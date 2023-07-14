package com.example.drone.model.viewmodel;

import java.util.List;

public class LoadRequestVm {
	Integer droneId;
	List<MedicineVm> medicines;
	public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
	public List<MedicineVm> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<MedicineVm> medicines) {
		this.medicines = medicines;
	}
}
