package com.example.drone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drone.model.view.DroneVm;
import com.example.drone.model.view.LoadRequestVm;
import com.example.drone.model.view.MedicineVm;
import com.example.drone.repository.BatteryCapacityRepository;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.LoadMedicationRepository;
import com.example.drone.repository.LoadRepository;
import com.example.drone.repository.MedicationRepository;
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
		  List<Drone> drones  = droneRepository.findByState("IDLE");
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
			  status = "wrong drone ID";
		  }
		  else if(!droneOpt.get().getState().equals("IDLE")) {
			  status = "requested drone is not idle ";
		  }
		  else if(!this.checkWeight(droneOpt.get(),loadRequestVm)){
			  status = "Drone can not handle the weight";
		  }
		  else if(!this.checkBattery(loadRequestVm.getDroneId())){
			  status = "Low Battery power";
		  }else {
			  Drone drone = droneOpt.get();
			  drone.setState("LOADED");
		      droneRepository.save(drone);
				  
			  Load load = new Load();
		      load.setLoadStatus("LOADED");
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
			  status = "success";
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

	private Integer calculateTotalweight(List<MedicineVm> medicines) {
		Integer total=0;
		for(MedicineVm medicineVm:medicines){
			Optional<Medication> medication = medicationRepository.findById(medicineVm.getMedicineId());
			total = total + (medicineVm.getQuantity() * medication.get().getWeight());
		}
		return total;
	}

	private boolean checkBattery(Integer droneId) {
		Drone drone = new Drone(droneId);
		List<BatteryCapacity> batteryCapacities = batteryCapacityRepository.findByDroneIdAndRecordStatus(drone,"ACTIVE");
		BatteryCapacity batteryCapacity = batteryCapacities.get(0);
		if(batteryCapacity.getPercentage()<25) {
			return false;
		}
		else{
			return true;
		}
	}

	public List<MedicationDto> getMedicineForDrone(Integer droneId) {
		Optional<Drone> droneOpt = droneRepository.findById(droneId);
		System.out.println("droneOpt.get().getWeightLimit()" + droneOpt.get().getWeightLimit());
		List<Medication> medications = medicationRepository.findByWeightLessThanEqual(droneOpt.get().getWeightLimit());
		System.out.println("medications.size() " + medications.size() );
		List<MedicationDto> medicationDtos = new ArrayList<MedicationDto>();
		 for(Medication medication: medications){
			 MedicationDto MedicationDto = new MedicationDto();
			 MedicationDto.setMedicationId(medication.getMedicationId());
			 MedicationDto.setCode(medication.getCode());
			 MedicationDto.setName(medication.getName());
			 MedicationDto.setWeight(medication.getWeight());
			 if(medication.getImage() != null){
				 MedicationDto.setImage(medication.getImage());
			 }
			 medicationDtos.add(MedicationDto);
		 }
		 return medicationDtos;
	}
}
