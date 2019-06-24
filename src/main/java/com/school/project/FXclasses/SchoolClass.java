package com.school.project.FXclasses;

public class SchoolClass {
    private int classID;
    private String className;
    private int fkStudentID;
    private int fkTeacherID;

    public SchoolClass(int classID, String className, int fkStudentID, int fkTeacherID) {
        this.classID = classID;
        this.className = className;
        this.fkStudentID = fkStudentID;
        this.fkTeacherID = fkTeacherID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getFkStudentID() {
        return fkStudentID;
    }

    public void setFkStudentID(int fkStudentID) {
        this.fkStudentID = fkStudentID;
    }

    public int getFkTeacherID() {
        return fkTeacherID;
    }

    public void setFkTeacherID(int fkTeacherID) {
        this.fkTeacherID = fkTeacherID;
    }
}
