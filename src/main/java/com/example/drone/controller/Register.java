package com.example.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.view.DroneVm;
import com.example.drone.service.RegisterService;

@RestController
@RequestMapping("/register")
public class Register {
	@Autowired
	RegisterService register;
	
    @PostMapping("/request")
    public String request(@RequestBody DroneVm droneVm) {
    	System.out.println("name" + droneVm.getWeightLimit());
    	register.saveData(droneVm);
        return "sucess";
    }
}
