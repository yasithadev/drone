package com.example.drone.model.viewmodel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SampleUserForTest {
    @NotNull(message = "First Name can not be empty")
    String firstName;
    @NotNull(message = "Last Name can not be empty")
    String lastName;
    @NotNull(message = "Email can not be empty")
    String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
