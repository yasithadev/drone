/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.drone.model.persistent;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author yasitha
 */
@Entity
@Table(name = "drone")
/*
@NamedQueries({
    @NamedQuery(name = "Drone.findAll", query = "SELECT d FROM Drone d"),
    @NamedQuery(name = "Drone.findByDroneId", query = "SELECT d FROM Drone d WHERE d.droneId = :droneId"),
    @NamedQuery(name = "Drone.findBySerialNumber", query = "SELECT d FROM Drone d WHERE d.serialNumber = :serialNumber"),
    @NamedQuery(name = "Drone.findByModel", query = "SELECT d FROM Drone d WHERE d.model = :model"),
    @NamedQuery(name = "Drone.findByWeightLimit", query = "SELECT d FROM Drone d WHERE d.weightLimit = :weightLimit"),
    @NamedQuery(name = "Drone.findByState", query = "SELECT d FROM Drone d WHERE d.state = :state")})
    */
public class Drone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "drone_id")
    private Integer droneId;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "model")
    private String model;
    @Column(name = "weight_limit")
    private Integer weightLimit;
    @Column(name = "state")
    private String state;//IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "droneId")
    private Collection<Load> loadCollection;
    @OneToMany(mappedBy = "droneId")
    private Collection<BatteryCapacity> batteryCapacityCollection;

    public Drone() {
    }

    public Drone(Integer droneId) {
        this.droneId = droneId;
    }

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

    public Collection<Load> getLoadCollection() {
        return loadCollection;
    }

    public void setLoadCollection(Collection<Load> loadCollection) {
        this.loadCollection = loadCollection;
    }

    public Collection<BatteryCapacity> getBatteryCapacityCollection() {
        return batteryCapacityCollection;
    }

    public void setBatteryCapacityCollection(Collection<BatteryCapacity> batteryCapacityCollection) {
        this.batteryCapacityCollection = batteryCapacityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (droneId != null ? droneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drone)) {
            return false;
        }
        Drone other = (Drone) object;
        if ((this.droneId == null && other.droneId != null) || (this.droneId != null && !this.droneId.equals(other.droneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.jpa.Drone[ droneId=" + droneId + " ]";
    }
    
}
