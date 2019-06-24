package com.school.project.FXclasses;

public class Subject {
    private int subjectID;
    private String subjectName;
    private int subjectStatus;
    private int fkClassKeyID;

    public Subject(int subjectID, String subjectName, int subjectStatus, int fkClassKeyID) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectStatus = subjectStatus;
        this.fkClassKeyID = fkClassKeyID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(int subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public int getFkClassKeyID() {
        return fkClassKeyID;
    }

    public void setFkClassKeyID(int fkClassKeyID) {
        this.fkClassKeyID = fkClassKeyID;
    }
}
