package com.coderscampus.cp.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.coderscampus.cp.dto.StudentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student  {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(nullable = false, unique=true)
    private String uid;
    private String name;
    private Integer assignmentNum;
    private String ide;
    private Instant dateCreated;
    private Boolean willingToMentor;
    private String mentee;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private GitHub githubHandle;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private LinkedIn linkedIn;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private YouTube youtube;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private FinalProject finalProject;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Resume resume;
//    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Foobar foobar;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Networking networking;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Website website;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Checkin> checkin = new ArrayList<Checkin>();

    public Student() {
    	this.dateCreated = Instant.now();
    }

    public Student(String uid, String name, Integer assignmentNum, String ide,
			Boolean willingToMentor, String mentee, List<Checkin> checkin) {
		this();
		this.uid = uid;
		this.name = name;
		this.assignmentNum = assignmentNum;
		this.ide = ide;
		this.willingToMentor = willingToMentor;
		this.mentee = mentee;
		this.checkin = checkin;
	}

	public Student(StudentDTO studentDTO) {
        this();
        Long id = studentDTO.getId();
        if(id != null && id > 0){
            this.id = id;
        }

		this.name = studentDTO.getName();
		this.assignmentNum = studentDTO.getAssignmentNum();
		this.ide = studentDTO.getIde();
        this.willingToMentor = studentDTO.getWillingToMentor();
        this.mentee = studentDTO.getMentee();
	}

	public Instant getDateCreated() {
        return dateCreated;
    }

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }
    
    
//	public GitHub getGithubHandle() {
//		return githubHandle;
//	}
//
//	public void setGithubHandle(GitHub githubHandle) {
//		this.githubHandle = githubHandle;
//	}
//
//	public LinkedIn getLinkedIn() {
//		return linkedIn;
//	}
//
//	public void setLinkedIn(LinkedIn linkedIn) {
//		this.linkedIn = linkedIn;
//	}
//
//
//	public YouTube getYoutube() {
//		return youtube;
//	}
//
//	public void setYoutube(YouTube youtube) {
//		this.youtube = youtube;
//	}
//
//	public FinalProject getFinalProject() {
//		return finalProject;
//	}
//
//	public void setFinalProject(FinalProject finalProject) {
//		this.finalProject = finalProject;
//	}
//
//	public Resume getResume() {
//		return resume;
//	}
//
//	public void setResume(Resume resume) {
//		this.resume = resume;
//	}
//
//	public Foobar getFoobar() {
//		return foobar;
//	}
//
//	public void setFoobar(Foobar foobar) {
//		this.foobar = foobar;
//	}
//
//	public Networking getNetworking() {
//		return networking;
//	}
//
//	public void setNetworking(Networking networking) {
//		this.networking = networking;
//	}
//
//	public Website getWebsite() {
//		return website;
//	}
//
//	public void setWebsite(Website website) {
//		this.website = website;
//	}

    public Boolean getWillingToMentor() {
		return willingToMentor;
	}

	public void setWillingToMentor(Boolean willingToMentor) {
		this.willingToMentor = willingToMentor;
	}

	public String getMentee() {
		return mentee;
	}

	public void setMentee(String mentee) {
		this.mentee = mentee;
	}

	public List<Checkin> getCheckin() {
        return checkin;
    }

    public void setCheckin(List<Checkin> checkin) {
        this.checkin = checkin;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", assignmentNum=" + assignmentNum +
                ", ide='" + ide + '\'' +
                ", dateCreated=" + dateCreated +
                ", checkin=" + checkin +
                '}';
    }
    //	public Student(long id, String uid, String name, Integer assignmentNum, GitHub githubHandle, LinkedIn linkedIn, String ide, YouTube youtube,
//				   FinalProject finalProject, Resume resume, Foobar foobar, Networking networking, Website website, List<Checkin> checkin) {
//		this();
//		this.id = id;
//		this.uid = uid;
//		this.name = name;
//		this.assignmentNum = assignmentNum;
//		this.githubHandle = githubHandle;
//		this.linkedIn = linkedIn;
//		this.ide = ide;
//		this.youtube = youtube;
//		this.finalProject = finalProject;
//		this.resume = resume;
//		this.foobar = foobar;
//		this.networking = networking;
//		this.website = website;
//		this.checkin = checkin;
//	}

}
