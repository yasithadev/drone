package com.example.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Medication;

public interface MedicationRepository extends  JpaRepository<Medication, Integer>{
	List<Medication> findByWeightLessThanEqual(Integer weight);
}
