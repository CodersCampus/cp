package com.coderscampus.cp.dto;

public class WorkLogDTO {

    private String studentName;
    private Integer numberOfEntries;
    private Integer numberMinutesWorkDone;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public Integer getNumberMinutesWorkDone() {
        return numberMinutesWorkDone;
    }

    public void setNumberMinutesWorkDone(Integer numberMinutesWorkDone) {
        this.numberMinutesWorkDone = numberMinutesWorkDone;
    }
}

