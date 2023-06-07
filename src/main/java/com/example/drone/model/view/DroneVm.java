package com.example.drone.model.view;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DroneVm {
	@Size(max = 100, message = "serial Number should not be more than 100 characters")
	String serialNumber;
	@Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight", 
			message = "Incorrect model type. must match \"Lightweight|Middleweight|Cruiserweight|Heavyweight\"")
    String model;
	@Max(value = 500, message = "Weight Limit should not be greater than 500")
    Integer weightLimit;
    
    public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}
}
