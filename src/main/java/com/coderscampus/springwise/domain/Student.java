package com.coderscampus.springwise.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String uid;
	private String name;
	private Integer assignmentNum;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private GitHub githubHandle;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private LinkedIn linkedIn;
	private String ide;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private YouTube youtube;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FinalProject finalProject;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Resume resume;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private Foobar foobar;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Networking networking;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Website website;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Checkin> checkin = new ArrayList<Checkin>();
	public Networking getNetworking() {
		return networking;
	}

	public void setNetworking(Networking networking) {
		this.networking = networking;
	}

	

	public Student() {}



	public Student(long id, String name, Integer assignmentNum, GitHub githubHandle, LinkedIn linkedIn, String ide,
			String uid, YouTube youtube, FinalProject finalProject, Resume resume, Networking networking, Website website) {
		super();
		this.id = id;
		this.name = name;
		this.assignmentNum = assignmentNum;
		this.githubHandle = githubHandle;
		this.linkedIn = linkedIn;
		this.ide = ide;
		this.uid = uid;
		this.youtube = youtube;
		this.finalProject = finalProject;
		this.resume = resume;
		this.networking = networking;
		this.website = website;
	}


	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
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
	public FinalProject getFinalProject() { return finalProject; }
	public void setFinalProject(FinalProject finalProject) { this.finalProject = finalProject; }
	public Resume getResume() { return resume; }
	public void setResume(Resume resume) { this.resume = resume; }


	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", uid='" + uid + '\'' +
				", name='" + name + '\'' +
				", assignmentNum=" + assignmentNum +
				", githubHandle=" + githubHandle +
				", linkedIn=" + linkedIn +
				", ide='" + ide + '\'' +
				", youtube=" + youtube +
				", finalProject=" + finalProject +
				", resume=" + resume +
				", foobar=" + foobar +
				", networking=" + networking +
				", website=" + website +
				'}';
	}
}
