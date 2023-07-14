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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;

/**
 *
 * @author yasitha
 */
@Builder
@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medication_id")
    private Integer medicationId;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "code")
    private String code;
    @Lob
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicationId")
    private Collection<LoadMedication> loadMedicationCollection;

    public Medication() {
    }
	public Medication(Integer medicationId, String name, Integer weight, String code, String image,
			Collection<LoadMedication> loadMedicationCollection) {
		super();
		this.medicationId = medicationId;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
		this.loadMedicationCollection = loadMedicationCollection;
	}
    public Medication(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<LoadMedication> getLoadMedicationCollection() {
        return loadMedicationCollection;
    }

    public void setLoadMedicationCollection(Collection<LoadMedication> loadMedicationCollection) {
        this.loadMedicationCollection = loadMedicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicationId != null ? medicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.medicationId == null && other.medicationId != null) || (this.medicationId != null && !this.medicationId.equals(other.medicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.jpa.Medication[ medicationId=" + medicationId + " ]";
    }
   
}
