package com.example.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.drone.model.persistent.Drone;

public interface DroneRepository  extends  JpaRepository<Drone, Integer>{
	List<Drone> findByState(String state);
}
