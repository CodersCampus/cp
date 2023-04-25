package com.coderscampus.springwise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Truck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String motorSize;
	private String modelName;
	

	
	@Override
	public String toString() {
		return "Truck [id=" + id + ", motorSize=" + motorSize + ", modelName=" + modelName + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMotorSize() {
		return motorSize;
	}
	public void setMotorSize(String motorSize) {
		this.motorSize = motorSize;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
}
