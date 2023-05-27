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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author yasitha
 */
@Entity
@Table(name = "load_")
/*
@NamedQueries({
    @NamedQuery(name = "Load.findAll", query = "SELECT l FROM Load l"),
    @NamedQuery(name = "Load.findByLoadId", query = "SELECT l FROM Load l WHERE l.loadId = :loadId")})
*/
public class Load implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "load_id")
    private Integer loadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loadId")
    private Collection<LoadMedication> loadMedicationCollection;
    @JoinColumn(name = "drone_id", referencedColumnName = "drone_id")
    @ManyToOne(optional = false)
    private Drone droneId;

    public Load() {
    }

    public Load(Integer loadId) {
        this.loadId = loadId;
    }

    public Integer getLoadId() {
        return loadId;
    }

    public void setLoadId(Integer loadId) {
        this.loadId = loadId;
    }

    public Collection<LoadMedication> getLoadMedicationCollection() {
        return loadMedicationCollection;
    }

    public void setLoadMedicationCollection(Collection<LoadMedication> loadMedicationCollection) {
        this.loadMedicationCollection = loadMedicationCollection;
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
        hash += (loadId != null ? loadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Load)) {
            return false;
        }
        Load other = (Load) object;
        if ((this.loadId == null && other.loadId != null) || (this.loadId != null && !this.loadId.equals(other.loadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.jpa.Load[ loadId=" + loadId + " ]";
    }
    
}
