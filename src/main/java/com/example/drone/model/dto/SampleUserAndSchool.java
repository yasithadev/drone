package com.example.drone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleUserAndSchool {
    String firstName;
    String lastName;
    String email;
    School school;
}
