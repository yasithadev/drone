package com.example.drone.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.drone.model.persistent.BatteryCapacity;
import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Load;
import com.example.drone.model.viewmodel.BatteryPercentageVm;
import com.example.drone.model.viewmodel.DroneStateVm;
import com.example.drone.repository.BatteryCapacityRepository;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.LoadRepository;
import com.example.drone.util.BatteryRecordStatus;
import com.example.drone.util.DroneState;
import com.example.drone.util.LoadStatus;
import com.example.drone.util.SuccessCode;

@Service
public class SituationUpdateService {
	
	  @Autowired
	  private DroneRepository droneRepository;
	  
	  @Autowired
	  private BatteryCapacityRepository batteryCapacityRepository;
	  
	  @Autowired
	  private LoadRepository loadRepository;
	  
	  @Transactional
	  public String updateDroneState(DroneStateVm droneStateVm) {

		  Optional<Drone> optDrone = droneRepository.findById(droneStateVm.getDroneId());
		  Drone drone = optDrone.get();
		  drone.setState(droneStateVm.getState());
		  droneRepository.save(drone);
		  if(droneStateVm.getState().equals(DroneState.DELIVERED)) {
			  List<Load> loads = loadRepository.findByDroneIdAndLoadStatus(drone,LoadStatus.LOADED);
			  Load load;
			  if(loads.size()>0) {
				  load = loads.get(0);
				  load.setLoadStatus(LoadStatus.DELIVERED);
				  loadRepository.save(load);
			  }
		  }
		  return SuccessCode.UPDATED_002;
	  }
	  
	@Transactional
	public String updateBatteryPercentage(BatteryPercentageVm batteryPercentageVm) {
		
		Drone drone = new Drone(batteryPercentageVm.getDroneId());
		List<BatteryCapacity> batteryCapacities = batteryCapacityRepository.findByDroneIdAndRecordStatus(drone,BatteryRecordStatus.ACTIVE);
		BatteryCapacity batteryCapacity;
		if(batteryCapacities.size()>0){
			batteryCapacity = batteryCapacities.get(0);
			batteryCapacity.setRecordStatus(BatteryRecordStatus.INACTIVE);
			batteryCapacityRepository.save(batteryCapacity);
		}
		batteryCapacity = new BatteryCapacity();
		batteryCapacity.setDroneId(drone);
		batteryCapacity.setPercentage(batteryPercentageVm.getPercentage());
		batteryCapacity.setRecordStatus(BatteryRecordStatus.ACTIVE);
		batteryCapacity.setCreatedTime(new Date());
		batteryCapacityRepository.save(batteryCapacity);

		return SuccessCode.UPDATED_002;
	}
}
