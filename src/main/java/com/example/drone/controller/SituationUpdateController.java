package com.example.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.viewmodel.BatteryPercentageVm;
import com.example.drone.model.viewmodel.DroneStateVm;
import com.example.drone.service.SituationUpdateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/situation-update")
public class SituationUpdateController {
	@Autowired
	SituationUpdateService situationService;
	
    @PutMapping("/drone-state")
    public String  DroneState(@RequestBody DroneStateVm droneStateVm) {
    	String status  = situationService.updateDroneState(droneStateVm);
        return status;
    }
    @PostMapping("/battery-percentage")
    public String  batteryPercentage(@RequestBody @Valid BatteryPercentageVm batteryPercentageVm) {
    	String status  = situationService.updateBatteryPercentage(batteryPercentageVm);
        return status;
    }
}
