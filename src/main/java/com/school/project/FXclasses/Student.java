package com.school.project.FXclasses;

public class Student {

    private int studentID;
    private String studFirstName;
    private String studLastName;
    private int studAge;
    private String studEmail;
    private String sudOldClass;
    private String studPassword;
    private int fkParentID;

    public Student(int studentID, String studFirstName, String studLastName, int studAge, String studEmail,
                   String sudOldClass, String studPassword, int fkParentID) {
        this.studentID = studentID;
        this.studFirstName = studFirstName;
        this.studLastName = studLastName;
        this.studAge = studAge;
        this.studEmail = studEmail;
        this.sudOldClass = sudOldClass;
        this.studPassword = studPassword;
        this.fkParentID = fkParentID;
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

    public int getStudAge() {
        return studAge;
    }

    public void setStudAge(int studAge) {
        this.studAge = studAge;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public String getSudOldClass() {
        return sudOldClass;
    }

    public void setSudOldClass(String sudOldClass) {
        this.sudOldClass = sudOldClass;
    }

    public String getStudPassword() {
        return studPassword;
    }

    public void setStudPassword(String studPassword) {
        this.studPassword = studPassword;
    }

    public int getFkParentID() {
        return fkParentID;
    }

    public void setFkParentID(int fkParentID) {
        this.fkParentID = fkParentID;
    }
}
