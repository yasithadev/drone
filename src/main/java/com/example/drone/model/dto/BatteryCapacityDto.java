package com.example.drone.model.dto;

import java.util.Date;

import com.example.drone.model.persistent.Drone;

public class BatteryCapacityDto {

	private Integer batteryCapacityId;
	private Integer droneId;
    private Integer percentage;
    private String recordStatus;
    private Date createdTime;
   
    
    public Integer getBatteryCapacityId() {
		return batteryCapacityId;
	}
	public void setBatteryCapacityId(Integer batteryCapacityId) {
		this.batteryCapacityId = batteryCapacityId;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
}
