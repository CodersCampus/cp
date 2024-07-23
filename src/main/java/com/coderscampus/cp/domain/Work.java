package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "work")
public class Work extends HasUidBase {
	private Integer assignmentNumber;
	private Integer numberMinutes;
	private String description;

	public Work(Integer assignmentNumber, Integer numberMinutes, String description) {
		super();
		this.assignmentNumber = assignmentNumber;
		this.numberMinutes = numberMinutes;
		this.description = description;
	}

	public Work() {
		super();
	}

	public Integer getAssignmentNumber() {
		return assignmentNumber;
	}

	public void setAssignmentNumber(Integer assignmentNumber) {
		this.assignmentNumber = assignmentNumber;
	}

	public Integer getNumberMinutes() {
		return numberMinutes;
	}

	public void setNumberMinutes(Integer numberMinutes) {
		this.numberMinutes = numberMinutes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Work [assignmentNumber=" + assignmentNumber + ", numberMinutes=" + numberMinutes
				+ ", description=" + description + "]";
	}

	
}
