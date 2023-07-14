package com.example.drone.service;

import com.example.drone.model.viewmodel.SampleUserForTest;

public interface SampleForTest {
    Long registerUser(SampleUserForTest user, boolean sendWelcomeMail);
}
