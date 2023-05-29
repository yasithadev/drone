package com.example.drone.model.view;

import jakarta.validation.constraints.Pattern;

public class MedicineRegistrationVm {
	
	@Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    private String name;
    private Integer weight;
    @Pattern(regexp = "^[A-Z0-9_.-]*$")
    private String code;
    private String image;

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
}
