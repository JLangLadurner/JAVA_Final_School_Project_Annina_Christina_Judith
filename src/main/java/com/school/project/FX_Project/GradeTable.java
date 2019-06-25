package com.school.project.FX_Project;

public class GradeTable {
    private int studGradeID;
    private int grade;
    private int fkSubjectID;
    private int fkStudentID;

    public GradeTable(int studGradeID, int grade, int fkSubjectID, int fkStudentID) {
        this.studGradeID = studGradeID;
        this.grade = grade;
        this.fkSubjectID = fkSubjectID;
        this.fkStudentID = fkStudentID;
    }

    public int getStudGradeID() {
        return studGradeID;
    }

    public void setStudGradeID(int studGradeID) {
        this.studGradeID = studGradeID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getFkSubjectID() {
        return fkSubjectID;
    }

    public void setFkSubjectID(int fkSubjectID) {
        this.fkSubjectID = fkSubjectID;
    }

    public int getFkStudentID() {
        return fkStudentID;
    }

    public void setFkStudentID(int fkStudentID) {
        this.fkStudentID = fkStudentID;
    }
}
