package com.coderscampus.springwise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Boolean blockers;
    private Integer assignment;
    private Boolean springWise;
    private Boolean available;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Boolean getBlockers() {
        return blockers;
    }
    public void setBlockers(Boolean blockers) {
        this.blockers = blockers;
    }
    public Integer getAssignment() {
        return assignment;
    }
    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }
    public Boolean getSpringWise() {
        return springWise;
    }
    public void setSpringWise(Boolean springWise) {
        this.springWise = springWise;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    @Override
    public String toString() {
        return "Checkin [id=" + id + ", date=" + date + ", blockers=" + blockers + ", assignment=" + assignment
                + ", springWise=" + springWise + ", available=" + available + "]";
    }


}
