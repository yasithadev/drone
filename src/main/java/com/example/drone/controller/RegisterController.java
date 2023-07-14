package com.example.drone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.drone.model.viewmodel.DroneVm;
import com.example.drone.model.viewmodel.MedicineRegistrationVm;
import com.example.drone.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService register;
	
    @PostMapping("/request")
    public String request(@RequestBody @Valid DroneVm droneVm) {
    	System.out.println("name" + droneVm.getWeightLimit());
    	register.saveData(droneVm);
        return "sucess";
    }
    @PostMapping("/new-medicine")
    public String newMedicine(@RequestBody @Valid MedicineRegistrationVm medicineRegistrationVm) {
    	System.out.println("code" + medicineRegistrationVm.getCode());
    	register.saveMedication(medicineRegistrationVm);
        return "sucess";
    }
}
