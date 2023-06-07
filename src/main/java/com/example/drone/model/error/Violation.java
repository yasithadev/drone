package com.example.drone.model.error;

public class Violation {

	private final String fieldName;
	private final String message;
	public Violation(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getMessage() {
		return message;
	}
}
