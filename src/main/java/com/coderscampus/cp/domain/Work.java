package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work extends HasUidBase {
    private String studentName;
    private Integer assignmentNumber;
    private Integer numberMinutes;
    private String description;


    public Work(String studentName, Integer assignmentNumber, Integer numberMinutes, String description) {
        super();
        this.studentName = studentName;
        this.assignmentNumber = assignmentNumber;
        this.numberMinutes = numberMinutes;
        this.description = description;


    }

    public Work() {
        super();
    }

    public void setDateBackDoorForTesting(Instant date){
        this.date = date;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(Integer assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public Integer getNumberMinutes() {
        return numberMinutes;
    }

    public void setNumberMinutes(Integer numberMinutes) {
        this.numberMinutes = numberMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Work{" +
                "studentName='" + studentName + '\'' +
                ", assignmentNumber=" + assignmentNumber +
                ", numberMinutes=" + numberMinutes +
                ", description='" + description + '\'' +
                '}';
    }
}
