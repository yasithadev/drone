/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.drone.model.persistent;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author yasitha
 */
@Entity
@Table(name = "battery_capacity")
/*
@NamedQueries({
    @NamedQuery(name = "BatteryCapacity.findAll", query = "SELECT b FROM BatteryCapacity b"),
    @NamedQuery(name = "BatteryCapacity.findByBatteryCapacityId", query = "SELECT b FROM BatteryCapacity b WHERE b.batteryCapacityId = :batteryCapacityId"),
    @NamedQuery(name = "BatteryCapacity.findByPercentage", query = "SELECT b FROM BatteryCapacity b WHERE b.percentage = :percentage"),
    @NamedQuery(name = "BatteryCapacity.findByCreatedTime", query = "SELECT b FROM BatteryCapacity b WHERE b.createdTime = :createdTime")})
    */
public class BatteryCapacity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "battery_capacity_id")
    private Integer batteryCapacityId;
    @Column(name = "percentage")
    private Integer percentage;
    @Column(name = "record_status")
    private String recordStatus;
	@Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @JoinColumn(name = "drone_id", referencedColumnName = "drone_id")
    @ManyToOne
    private Drone droneId;

    public BatteryCapacity() {
    }

    public BatteryCapacity(Integer batteryCapacityId) {
        this.batteryCapacityId = batteryCapacityId;
    }

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

    public Drone getDroneId() {
        return droneId;
    }

    public void setDroneId(Drone droneId) {
        this.droneId = droneId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batteryCapacityId != null ? batteryCapacityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatteryCapacity)) {
            return false;
        }
        BatteryCapacity other = (BatteryCapacity) object;
        if ((this.batteryCapacityId == null && other.batteryCapacityId != null) || (this.batteryCapacityId != null && !this.batteryCapacityId.equals(other.batteryCapacityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.jpa.BatteryCapacity[ batteryCapacityId=" + batteryCapacityId + " ]";
    }
    
}
