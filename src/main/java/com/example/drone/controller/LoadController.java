package com.example.drone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.dto.DroneDto;
import com.example.drone.model.dto.MedicationDto;
import com.example.drone.model.persistent.Drone;
import com.example.drone.model.persistent.Medication;
import com.example.drone.model.view.DroneVm;
import com.example.drone.model.view.LoadRequestVm;
import com.example.drone.service.LoadService;
import com.example.drone.service.RegisterService;

@RestController
@RequestMapping("/load")
public class LoadController {
	@Autowired
	LoadService loadService;
	
    @GetMapping("/available")
    public List<DroneDto>   getAvailable() {
        return loadService.getAvailable();
    }
    //check loadable items for drone before load
    @GetMapping("/medicine-by-drone")
    public List<MedicationDto>  geMedicineForDrone(@RequestParam Integer droneId) {
    	System.out.println("droneId" + droneId);
        return loadService.getMedicineForDrone(droneId);
    }
    @PostMapping("/request")
    public String  request(@RequestBody LoadRequestVm loadRequestVm) {
    	System.out.println("loadRequestVm Id " + loadRequestVm.getDroneId());
    	System.out.println("loadRequestVm getMedicineId " + loadRequestVm.getMedicines().get(0).getMedicineId());
    	String status  = loadService.loadManager(loadRequestVm);
        return status;
    }
}
