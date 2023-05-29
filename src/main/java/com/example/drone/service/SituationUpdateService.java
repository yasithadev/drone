package com.example.drone.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drone.model.persistent.BatteryCapacity;
import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Load;
import com.example.drone.model.view.BatteryPercentageVm;
import com.example.drone.model.view.DroneStateVm;
import com.example.drone.repository.BatteryCapacityRepository;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.LoadRepository;

@Service
public class SituationUpdateService {
	
	  @Autowired
	  private DroneRepository droneRepository;
	  
	  @Autowired
	  private BatteryCapacityRepository batteryCapacityRepository;
	  
	  @Autowired
	  private LoadRepository loadRepository;

	  public String updateDroneState(DroneStateVm droneStateVm) {

		  Optional<Drone> optDrone = droneRepository.findById(droneStateVm.getDroneId());
		  Drone drone = optDrone.get();
		  drone.setState(droneStateVm.getState());
		  droneRepository.save(drone);
		  if(droneStateVm.getState().equals("DELIVERED")) {
			  List<Load> loads = loadRepository.findByDroneIdAndLoadStatus(drone,"LOADED");
			  Load load;
			  if(loads.size()>0) {
				  load = loads.get(0);
				  load.setLoadStatus("DELIVERED");
				  loadRepository.save(load);
			  }
		  }
		  return "success";
	  }

	public String updateBatteryPercentage(BatteryPercentageVm batteryPercentageVm) {
		
		Drone drone = new Drone(batteryPercentageVm.getDroneId());
		List<BatteryCapacity> batteryCapacities = batteryCapacityRepository.findByDroneIdAndRecordStatus(drone,"ACTIVE");
		BatteryCapacity batteryCapacity;
		if(batteryCapacities.size()>0){
			batteryCapacity = batteryCapacities.get(0);
			batteryCapacity.setRecordStatus("INACTIVE");
			batteryCapacityRepository.save(batteryCapacity);
		}
		batteryCapacity = new BatteryCapacity();
		batteryCapacity.setDroneId(drone);
		batteryCapacity.setPercentage(batteryPercentageVm.getPercentage());
		batteryCapacity.setRecordStatus("ACTIVE");
		batteryCapacity.setCreatedTime(new Date());
		batteryCapacityRepository.save(batteryCapacity);

		return "success";
	}
}
