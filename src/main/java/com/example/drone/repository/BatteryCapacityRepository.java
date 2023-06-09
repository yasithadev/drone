package com.example.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.BatteryCapacity;
import com.example.drone.model.persistent.Drone;
import java.util.Date;

public interface BatteryCapacityRepository extends JpaRepository<BatteryCapacity, Integer>{
	List<BatteryCapacity> findByDroneIdAndRecordStatus(Drone droneId,String recordStatus);
	List<BatteryCapacity> findAllByCreatedTimeBetween( Date startDate,Date endDate);
}
