package com.example.drone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Medication;
import com.example.drone.model.viewmodel.DroneVm;
import com.example.drone.model.viewmodel.MedicineRegistrationVm;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.MedicationRepository;

import jakarta.validation.Valid;
@Service
public class RegisterService {
	  @Autowired
	  private DroneRepository droneRepository;
	  
	  @Autowired
	  private MedicationRepository medicationRepository;
	 
	  @Transactional
	  public void saveData(DroneVm droneVm){
		  System.out.println("called");
		  Drone drone = new Drone();
		  drone.setSerialNumber(droneVm.getSerialNumber());
		  drone.setModel(droneVm.getModel());
		  drone.setWeightLimit(droneVm.getWeightLimit());
		  droneRepository.save(drone);
	  }

	public void saveMedication(@Valid MedicineRegistrationVm medicineRegistrationVm) {
		Medication medication =  new Medication();
		medication.setName(medicineRegistrationVm.getName());
		medication.setCode(medicineRegistrationVm.getCode());
		medication.setWeight(medicineRegistrationVm.getWeight());
		medication.setImage(medicineRegistrationVm.getImage());
		medicationRepository.save(medication);
		
	}
}
