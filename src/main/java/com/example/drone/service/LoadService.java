package com.example.drone.service;

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
import com.example.drone.model.persistent.*;

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
	  public List<Drone> getAvailable(){
		  //System.out.println("called");
		  return droneRepository.findByState("IDLE");
	  }
	 
	  @Transactional
	  public String loadManager(LoadRequestVm loadRequestVm){
		  //System.out.println("called");
		  String status;
		  if(!this.checkWeight(loadRequestVm)){
			  status = "Drone can not handle the weight";
		  }
		  else if(!this.checkBattery(loadRequestVm.getDroneId())){
			  status = "Low Battery power";
		  }else {
			  Load load = new Load();
			  Drone drone = new Drone(loadRequestVm.getDroneId());
			  load.setDroneId(drone);
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

	private boolean checkWeight(LoadRequestVm loadRequestVm) {
		  Optional<Drone> drone = droneRepository.findById(loadRequestVm.getDroneId());
		  if(drone.get().getWeightLimit() >= this.calculateTotalweight(loadRequestVm.getMedicines())) {
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
}
