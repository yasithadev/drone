package com.example.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Load;

public interface LoadRepository   extends  JpaRepository<Load, Integer>{
	List<Load> findByDroneIdAndLoadStatus(Drone drone,String Status);
}
