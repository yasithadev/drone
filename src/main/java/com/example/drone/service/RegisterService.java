package com.example.drone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.view.DroneVm;
import com.example.drone.repository.DroneRepository;
@Service
public class RegisterService {
	  @Autowired
	  private DroneRepository droneRepository;
	 
	  @Transactional
	  public void saveData(DroneVm droneVm){
		  System.out.println("called");
		  Drone drone = new Drone();
		  drone.setSerialNumber(droneVm.getSerialNumber());
		  drone.setModel(droneVm.getModel());
		  drone.setWeightLimit(droneVm.getWeightLimit());
		  droneRepository.save(drone);
	  }
}
