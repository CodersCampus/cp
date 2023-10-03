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
	private Integer motorSize;
	private String modelName;
	private String color;



	private String manufacturer;
	


	@Override
	public String toString() {
		return "Truck [id=" + id + ", motorSize=" + motorSize + ", modelName=" + modelName + ", manufacturer=" + manufacturer +", color=" + color + "]";
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMotorSize() {
		return motorSize;
	}
	public void setMotorSize(Integer motorSize) {
		this.motorSize = motorSize;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getManufacturer() {return manufacturer;}

	public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}
	
}
