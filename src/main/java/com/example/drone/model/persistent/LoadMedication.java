/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.drone.model.persistent;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author yasitha
 */
@Entity
@Table(name = "load_medication")
@NamedQueries({
    @NamedQuery(name = "LoadMedication.findAll", query = "SELECT l FROM LoadMedication l"),
    @NamedQuery(name = "LoadMedication.findByLoadMedicationId", query = "SELECT l FROM LoadMedication l WHERE l.loadMedicationId = :loadMedicationId"),
    @NamedQuery(name = "LoadMedication.findByQuantity", query = "SELECT l FROM LoadMedication l WHERE l.quantity = :quantity")})
public class LoadMedication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "load_medication_id")
    private Integer loadMedicationId;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "load_id", referencedColumnName = "load_id")
    @ManyToOne(optional = false)
    private Load loadId;
    @JoinColumn(name = "medication_id", referencedColumnName = "medication_id")
    @ManyToOne(optional = false)
    private Medication medicationId;

    public LoadMedication() {
    }

    public LoadMedication(Integer loadMedicationId) {
        this.loadMedicationId = loadMedicationId;
    }

    public LoadMedication(Integer loadMedicationId, int quantity) {
        this.loadMedicationId = loadMedicationId;
        this.quantity = quantity;
    }

    public Integer getLoadMedicationId() {
        return loadMedicationId;
    }

    public void setLoadMedicationId(Integer loadMedicationId) {
        this.loadMedicationId = loadMedicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Load getLoadId() {
        return loadId;
    }

    public void setLoadId(Load loadId) {
        this.loadId = loadId;
    }

    public Medication getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Medication medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loadMedicationId != null ? loadMedicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadMedication)) {
            return false;
        }
        LoadMedication other = (LoadMedication) object;
        if ((this.loadMedicationId == null && other.loadMedicationId != null) || (this.loadMedicationId != null && !this.loadMedicationId.equals(other.loadMedicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.jpa.LoadMedication[ loadMedicationId=" + loadMedicationId + " ]";
    }
    
}
