package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "work")
public class Work extends HasUidBase {

	private boolean worked;
	private Integer assignmentNumber;
	private Integer numberHours;
	private String description;

	public Work(boolean worked, Integer assignmentNumber, Integer numberHours, String description) {
		super();
		this.worked = worked;
		this.assignmentNumber = assignmentNumber;
		this.numberHours = numberHours;
		this.description = description;
	}

	public Work() {
		super();
	}

	public boolean isWorked() {
		return worked;
	}

	public void setWorked(boolean worked) {
		this.worked = worked;
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
		return "Work [worked=" + worked + ", assignmentNumber=" + assignmentNumber + ", numberHours=" + numberHours
				+ ", description=" + description + "]";
	}

	
}
