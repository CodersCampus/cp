package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class Barfoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String uid;
    private String foobar;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Barfoo() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFoobar() {
        return foobar;
    }

    public void setFoobar(String foobar) {
        this.foobar = foobar;
    }

    @Override
    public String toString() {
        return "Barfoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", student=" + student +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
