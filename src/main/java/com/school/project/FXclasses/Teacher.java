package com.school.project.FXclasses;

public class Teacher {
    private int teacherID;
    private String teaFirstName;
    private String teaLastName;
    private String teaEmail;

    public Teacher(int teacherID, String teaFirstName, String teaLastName, String teaEmail) {
        this.teacherID = teacherID;
        this.teaFirstName = teaFirstName;
        this.teaLastName = teaLastName;
        this.teaEmail = teaEmail;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeaFirstName() {
        return teaFirstName;
    }

    public void setTeaFirstName(String teaFirstName) {
        this.teaFirstName = teaFirstName;
    }

    public String getTeaLastName() {
        return teaLastName;
    }

    public void setTeaLastName(String teaLastName) {
        this.teaLastName = teaLastName;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }
}
