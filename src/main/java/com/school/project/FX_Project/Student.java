package com.school.project.FX_Project;

public class Student {

    private int studentID;
    private String studFirstName;
    private String studLastName;
    private String studOldClass;
    private int studNewClassID;

    public Student(int studentID, String studFirstName, String studLastName, String studOldClass, int studNewClassID) {
        this.studentID = studentID;
        this.studFirstName = studFirstName;
        this.studLastName = studLastName;
        this.studOldClass = studOldClass;
        this.studNewClassID = studNewClassID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudFirstName() {
        return studFirstName;
    }

    public void setStudFirstName(String studFirstName) {
        this.studFirstName = studFirstName;
    }

    public String getStudLastName() {
        return studLastName;
    }

    public void setStudLastName(String studLastName) {
        this.studLastName = studLastName;
    }

    public String getStudOldClass() {
        return studOldClass;
    }

    @Override
    public String toString() {
        return studFirstName +" " + studLastName;
    }

    public void setStudOldClass(String studOldClass) {
        this.studOldClass = studOldClass;
    }

    public int getStudNewClassID() {
        return studNewClassID;
    }

    public void setStudNewClassID(int studNewClassID) {
        this.studNewClassID = studNewClassID;
    }
}
