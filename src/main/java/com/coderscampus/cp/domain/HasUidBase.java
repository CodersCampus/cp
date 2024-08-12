package com.coderscampus.cp.domain;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class HasUidBase {
	// wants to be a valid UID but visually distinct and readable as pure fluff
	protected static final String fauxValidUid = "GFLUFF-" + UUID.randomUUID().toString();
			
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String uid;

    @Column(nullable = false)
	protected Instant date;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

    public HasUidBase() {
        this.uid = fauxValidUid;
        this.date = Instant.now();
    }

	public void removeUid() {
		this.uid = fauxValidUid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}


	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
