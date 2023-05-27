package com.example.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.LoadMedication;

public interface LoadMedicationRepository extends  JpaRepository<LoadMedication, Integer>{

}
