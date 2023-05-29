package com.example.drone.model.view;

import jakarta.validation.constraints.Max;

public class BatteryPercentageVm {
	Integer droneId;
	@Max(value = 100, message = "Battery percentage should not be greater than 100")
	Integer percentage;
	public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
}
