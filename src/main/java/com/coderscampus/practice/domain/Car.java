package com.coderscampus.practice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String motorSize;
	private String modelName;
	private Integer wheelSize;
	private String transmission;
	private String color;
	private Integer yearOf;
	private Integer price; 
		
	public Car() {};
	
	

	public Car(Long id, String motorSize, String modelName, Integer wheelSize, String transmission, String color,
			Integer yearOf, Integer price) {
		super();
		this.id = id;
		this.motorSize = motorSize;
		this.modelName = modelName;
		this.wheelSize = wheelSize;
		this.transmission = transmission;
		this.color = color;
		this.yearOf = yearOf;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", motorSize=" + motorSize + ", modelName=" + modelName + ", wheelSize=" + wheelSize
				+ ", transmission=" + transmission + ", color=" + color + ", yearOf=" + yearOf + ", price=" + price + "]";
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
	public Integer getWheelSize() {
		return wheelSize;
	}
	public void setWheelSize(Integer wheelSize) {
		this.wheelSize = wheelSize;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getYearOf() {
		return yearOf;
	}

	public void setYearOf(Integer yearOf) {
		this.yearOf = yearOf;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
