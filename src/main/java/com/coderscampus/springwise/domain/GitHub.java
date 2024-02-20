package com.coderscampus.springwise.domain;

import jakarta.persistence.*;

@Entity
public class GitHub {
	public GitHub() {

	}

	private String handle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String enhancedReadMe;
	private String renamedAssignments;
	private String pinnedRepos;
	private String externalLinks;
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnhancedReadMe() {
		return enhancedReadMe;
	}
	public void setEnhancedReadMe(String enhancedReadMe) {
		this.enhancedReadMe = enhancedReadMe;
	}
	public String getRenamedAssignments() {
		return renamedAssignments;
	}
	public void setRenamedAssignments(String renamedAssignments) {
		this.renamedAssignments = renamedAssignments;
	}
	public String getPinnedRepos() {
		return pinnedRepos;
	}
	public void setPinnedRepos(String pinnedRepos) {
		this.pinnedRepos = pinnedRepos;
	}
	public String getExternalLinks() {
		return externalLinks;
	}
	public void setExternalLinks(String externalLinks) {
		this.externalLinks = externalLinks;
	}

	public GitHub(String handle, String enhancedReadMe, String renamedAssignments, String pinnedRepos,
				  String externalLinks) {
		super();
		this.handle = handle;
		this.enhancedReadMe = enhancedReadMe;
		this.renamedAssignments = renamedAssignments;
		this.pinnedRepos = pinnedRepos;
		this.externalLinks = externalLinks;

	}
	@Override
	public String toString() {
		return "GitHub [handle=" + handle + ", id=" + id + ", enhancedReadMe=" + enhancedReadMe
				+ ", renamedAssignments=" + renamedAssignments + ", pinnedRepos=" + pinnedRepos + ", externalLinks="
				+ externalLinks + "]";
	}



}
