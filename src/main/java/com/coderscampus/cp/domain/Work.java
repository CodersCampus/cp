package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "work")
public class Work extends HasUidBase {
	private Integer assignmentNumber;
	private Integer numberHours;
	private String description;

	public Work(Integer assignmentNumber, Integer numberHours, String description) {
		super();
		this.assignmentNumber = assignmentNumber;
		this.numberHours = numberHours;
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

	public Integer getNumberHours() {
		return numberHours;
	}

	public void setNumberHours(Integer numberHours) {
		this.numberHours = numberHours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Work [assignmentNumber=" + assignmentNumber + ", numberHours=" + numberHours
				+ ", description=" + description + "]";
	}

	
}
