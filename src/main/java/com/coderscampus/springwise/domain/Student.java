package com.coderscampus.springwise.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

	public Student() {
		
	}
	
	public Student(long id, String name, Integer assignmentNum, GitHub githubHandle, String ide, String uid) {
		super();
		this.id = id;
		this.name = name;
		this.assignmentNum = assignmentNum;
		this.githubHandle = githubHandle;
		this.ide = ide;
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", assignmentNum=" + assignmentNum + ", githubHandle="
				+ githubHandle + ", ide=" + ide + ", uid=" + uid + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Integer assignmentNum;
	
	private GitHub githubHandle;
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
	public GitHub getGithubHandle() {
		return githubHandle;
	}
	public void setGithubHandle(GitHub githubHandle) {
		this.githubHandle = githubHandle;
	}
	public String getIde() {
		return ide;
	}
	public void setIde(String ide) {
		this.ide = ide;
	}
	
	
	
	
	
}
