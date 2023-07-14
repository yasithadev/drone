package com.example.drone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drone.model.viewmodel.LoadRequestVm;
import com.example.drone.model.viewmodel.MedicineVm;
import com.example.drone.repository.BatteryCapacityRepository;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.LoadMedicationRepository;
import com.example.drone.repository.LoadRepository;
import com.example.drone.repository.MedicationRepository;
import com.example.drone.util.BatteryRecordStatus;
import com.example.drone.util.DroneState;
import com.example.drone.util.ErrorCode;
import com.example.drone.util.LoadStatus;
import com.example.drone.util.SuccessCode;
import com.example.drone.model.dto.MedicationDto;
import com.example.drone.model.persistent.*;
import com.example.drone.model.dto.DroneDto;


@Service
public class LoadService {
	  
	  @Autowired
	  private DroneRepository droneRepository;
	  
	  @Autowired
	  private LoadRepository loadRepository;
	  
	  @Autowired
	  private LoadMedicationRepository loadMedicationRepository;
	  
	  @Autowired
	  private MedicationRepository medicationRepository;
	  
	  @Autowired
	  private BatteryCapacityRepository batteryCapacityRepository;
	  
	  @Transactional
	  public List<DroneDto> getAvailable(){
		  //System.out.println("called");
		  List<Drone> drones  = droneRepository.findByState(DroneState.IDLE);
		  List<DroneDto> droneDtos = new ArrayList<DroneDto>();
		  for(Drone drone:drones) {
			  DroneDto droneDto = new DroneDto();
			  droneDto.setDroneId(drone.getDroneId());
			  droneDto.setSerialNumber(drone.getSerialNumber());
			  droneDto.setModel(drone.getModel());
			  droneDto.setWeightLimit(drone.getWeightLimit());
			  droneDto.setState(drone.getState());
			  droneDtos.add(droneDto);
		  }
		  return droneDtos;
	  }
	 
	  @Transactional
	  public String loadManager(LoadRequestVm loadRequestVm){
		  //System.out.println("called");
		  String status;
		  Optional<Drone> droneOpt = droneRepository.findById(loadRequestVm.getDroneId());
		  if(!droneOpt.isPresent()) {
			  status = ErrorCode.INCORRECT_DRONE_ID_102;
		  }
		  else if(!droneOpt.get().getState().equals(DroneState.IDLE)) {
			  status = ErrorCode.DRONE_NOT_IDLE_103;
		  }
		  else if(!this.checkWeight(droneOpt.get(),loadRequestVm)){
			  status = ErrorCode.OVER_WEIGHT_104;
		  }
		  else if(!this.checkBattery(loadRequestVm.getDroneId())){
			  status = ErrorCode.BATTERY_LOW_105;
		  }else {
			  Drone drone = droneOpt.get();
			  drone.setState(DroneState.LOADED);
		      droneRepository.save(drone);
				  
			  Load load = new Load();
		      load.setLoadStatus(LoadStatus.LOADED);
			  //Drone drone = new Drone(loadRequestVm.getDroneId());
			  load.setDroneId(droneOpt.get());
			  load = loadRepository.save(load);
				  
			  LoadMedication loadMedication;
			  for(MedicineVm medicineVm:loadRequestVm.getMedicines()){
					 
				  loadMedication = new LoadMedication();
				  loadMedication.setLoadId(load);
					  
				  Medication medication = new Medication();
				  medication.setMedicationId(medicineVm.getMedicineId());
				  loadMedication.setMedicationId(medication);
					  
				  loadMedication.setQuantity(medicineVm.getQuantity());
				  loadMedicationRepository.save(loadMedication);
			  }
			  status = SuccessCode.LOADED_002;
		  }
		  return status;
	  }

		private boolean checkWeight(Drone drone,LoadRequestVm loadRequestVm) {
		  if(drone.getWeightLimit() >= this.calculateTotalweight(loadRequestVm.getMedicines())) {
			  	return true;
		  }
		  else {
			  return false;
		  }
	  }

	public Integer calculateTotalweight(List<MedicineVm> medicines) {
		Integer total=0;
		for(MedicineVm medicineVm:medicines){
			Optional<Medication> medication = medicationRepository.findById(medicineVm.getMedicineId());
			//System.out.println("medicineVm.getQuantity()" + medicineVm.getQuantity() + "medication.get().getWeight()" + medication.get().getWeight());
			total = total + (medicineVm.getQuantity() * medication.get().getWeight());
		}
		return total;
	}

	private boolean checkBattery(Integer droneId) {
		Drone drone = new Drone(droneId);
		List<BatteryCapacity> batteryCapacities = batteryCapacityRepository.findByDroneIdAndRecordStatus(drone,BatteryRecordStatus.ACTIVE);
		BatteryCapacity batteryCapacity;
		if(batteryCapacities.size()>0){
			batteryCapacity = batteryCapacities.get(0);
		}else {
			return false;//TODO:in this case method should return object with message "battery percentage record not found"
		}
		if(batteryCapacity.getPercentage()<25) {
			return false;
		}
		else{
			return true;
		}
	}

	public List<MedicationDto> getMedicineForDrone(Integer droneId) {
		Optional<Drone> droneOpt = droneRepository.findById(droneId);
		List<Load> loads = loadRepository.findByDroneIdAndLoadStatus(droneOpt.get(),LoadStatus.LOADED);
		List<MedicationDto> medicationDtos = new ArrayList<MedicationDto>();
		if(loads.size()>0) {
			 Load load  = loads.get(0);
			 for(LoadMedication loadMedication:load.getLoadMedicationCollection()){
				 MedicationDto MedicationDto = new MedicationDto();
				 MedicationDto.setMedicationId(loadMedication.getMedicationId().getMedicationId());
				 MedicationDto.setCode(loadMedication.getMedicationId().getCode());
				 MedicationDto.setName(loadMedication.getMedicationId().getName());
				 MedicationDto.setWeight(loadMedication.getMedicationId().getWeight());
				 if(loadMedication.getMedicationId().getImage() != null){
					 MedicationDto.setImage(loadMedication.getMedicationId().getImage());
				 }
				 medicationDtos.add(MedicationDto);
			 }
		}
		return medicationDtos;
	}
}
