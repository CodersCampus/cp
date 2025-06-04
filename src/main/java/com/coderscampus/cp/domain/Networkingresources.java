package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class Networkingresources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String uid;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String resourceName;
    private String type;
    private String cost;
    private String geographicScope;
    private String notes;

    public Networkingresources() {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getGeographicScope() {
        return geographicScope;
    }

    public void setGeographicScope(String geographicScope) {
        this.geographicScope = geographicScope;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Networkingresources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", student=" + student +
                ", resourceName='" + resourceName + '\'' +
                ", type='" + type + '\'' +
                ", cost='" + cost + '\'' +
                ", geographicScope='" + geographicScope + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
