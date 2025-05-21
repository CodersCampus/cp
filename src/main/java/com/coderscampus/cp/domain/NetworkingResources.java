package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class NetworkingResources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private Student student;
    private String resourceName;
    private String type;
    private String cost;
    private String local;
    private String national;
    private String notes;

    public NetworkingResources() {
    }

    public NetworkingResources(Long id, String resourceName, String type, String cost, String local, String national, String notes) {
        this.id = id;
        this.resourceName = resourceName;
        this.type = type;
        this.cost = cost;
        this.local = local;
        this.national = national;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NetworkingResources [" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", type='" + type + '\'' +
                ", cost='" + cost + '\'' +
                ", local='" + local + '\'' +
                ", national='" + national + '\'' +
                ", notes='" + notes + '\'' +
                ']';
    }
}
