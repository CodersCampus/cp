package com.coderscampus.springwise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// to be replaced by reference to User table
	private String name;
	private Integer assignmentNum;
	private String githubHandle;
	private String ide;
	private String uid;
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAssignmentNum() {
		return assignmentNum;
	}
	public void setAssignmentNum(Integer assignmentNum) {
		this.assignmentNum = assignmentNum;
	}
	public String getGithubHandle() {
		return githubHandle;
	}
	public void setGithubHandle(String githubHandle) {
		this.githubHandle = githubHandle;
	}
	public String getIde() {
		return ide;
	}
	public void setIde(String ide) {
		this.ide = ide;
	}
	
	
	
	
	
}
