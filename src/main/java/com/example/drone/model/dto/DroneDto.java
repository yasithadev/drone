package com.example.drone.model.dto;

import jakarta.persistence.Column;

public class DroneDto {
	private Integer droneId;
    private String serialNumber;
    private String model;
    private Integer weightLimit;
    private String state;
    public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
