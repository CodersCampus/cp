package com.coderscampus.springwise.domain;

import jakarta.persistence.*;

@Entity
public class Student {

	public Student() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Integer assignmentNum;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private GitHub githubHandle;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private LinkedIn linkedIn;
	private String ide;
	private String uid;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private YouTube youtube;

	
	public YouTube getYoutube() {
		return youtube;
	}
	public void setYoutube(YouTube youtube) {
		this.youtube = youtube;
	}
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
	public LinkedIn getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(LinkedIn linkedIn) {
		this.linkedIn = linkedIn;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", assignmentNum=" + assignmentNum +
				", githubHandle=" + githubHandle +
				", linkedIn=" + linkedIn +
				", ide='" + ide + '\'' +
				", uid='" + uid + '\'' +
				", youtube" + youtube + '\'' +
				'}';
	}
}
