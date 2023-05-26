package com.example.drone.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.drone.model.persistent.Drone;

public interface DroneRepository  extends  CrudRepository<Drone, Integer>{
	
}
