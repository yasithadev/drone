package com.example.drone.controller;

import com.example.drone.model.dto.SampleUserAndSchool;
import com.example.drone.model.dto.School;
import com.example.drone.model.viewmodel.SampleUserForTest;
import com.example.drone.service.SampleForTest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-sample")
public class SampleControllerForTesting {
    @Autowired
    SampleForTest sampleForTest;
    @GetMapping("/mvc-testing")
    public String droneBatteryLevelAuditReport(){
            return "hello";

    }
    @PostMapping("/mvc-testing-post")
    public SampleUserForTest droneBatteryLevelAuditReport(@RequestBody @Valid SampleUserForTest sampleUser ){
        System.out.println(sampleUser.getFirstName() + " " + sampleUser.getFirstName() + " " + sampleUser.getEmail());
        sampleForTest.registerUser(sampleUser,true);
        System.out.println("returned");
        return sampleUser;
    }

    @PostMapping("/user-with-school-post")
    public SampleUserAndSchool retrnObjectWithReferenceObjects(@RequestBody @Valid SampleUserForTest sampleUser ){
        sampleForTest.registerUser(sampleUser,true);
        return SampleUserAndSchool.builder()
                .firstName("Yasitha")
                .lastName("Bandara")
                .email("Illukgoda")
                .school(School.builder().name("Nalanda Collage").district("Colombo 10").build())
                .build();
    }
}
