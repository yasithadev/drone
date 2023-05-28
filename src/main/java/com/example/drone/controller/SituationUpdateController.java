package com.example.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.view.BatteryPercentageVm;
import com.example.drone.model.view.DroneStateVm;
import com.example.drone.model.view.LoadRequestVm;
import com.example.drone.service.LoadService;
import com.example.drone.service.SituationUpdateService;

@RestController
@RequestMapping("/situation-update")
public class SituationUpdateController {
	@Autowired
	SituationUpdateService situationService;
	
    @PutMapping("/drone-state")
    public String  DroneState(@RequestBody DroneStateVm droneStateVm) {
    	//System.out.println("DroneStateVm Id " + droneStateVm.getDroneId());
    	String status  = situationService.updateDroneState(droneStateVm);
        return status;
    }
    @PostMapping("/battery-percentage")
    public String  batteryPercentage(@RequestBody BatteryPercentageVm batteryPercentageVm) {
    	//System.out.println("loadRequestVm Id " + loadRequestVm.getDroneId());
    	//System.out.println("loadRequestVm getMedicineId " + loadRequestVm.getMedicines().get(0).getMedicineId());
    	String status  = situationService.updateBatteryPercentage(batteryPercentageVm);
        return status;
    }
}
