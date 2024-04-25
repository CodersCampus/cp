package com.coderscampus.cp.dto;

import java.time.Instant;

import com.coderscampus.cp.domain.Student;

public class StudentDTO {

	private long id;
	private String name;
	private Integer assignmentNum;
	private String ide;
	private Instant dateCreated;
	

	public StudentDTO(Student foundStudent) {
		this.id = foundStudent.getId();
		this.name = foundStudent.getName();
		this.assignmentNum = foundStudent.getAssignmentNum();
		this.ide = foundStudent.getIde();
		this.dateCreated = foundStudent.getDateCreated();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAssignmentNum() {
		return assignmentNum;
	}

	public String getIde() {
		return ide;
	}

	public Instant getDateCreated() {
		return dateCreated;
	}

	



}
