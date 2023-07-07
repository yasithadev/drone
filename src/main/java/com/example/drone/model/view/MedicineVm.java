package com.example.drone.model.view;

import lombok.Builder;

@Builder
public class MedicineVm {

	Integer medicineId;
	Integer quantity;
	
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
