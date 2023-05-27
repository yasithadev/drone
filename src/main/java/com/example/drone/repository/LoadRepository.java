package com.example.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.model.persistent.Load;

public interface LoadRepository   extends  JpaRepository<Load, Integer>{

}
