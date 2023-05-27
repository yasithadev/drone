package com.example.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Medication;

public interface MedicationRepository extends  JpaRepository<Medication, Integer>{

}
