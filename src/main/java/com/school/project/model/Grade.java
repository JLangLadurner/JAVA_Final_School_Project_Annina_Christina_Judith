package com.school.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Grade {

    @EmbeddedId
    GradeKey id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subjectId")
    Subject subject;

    String grade;

    @Override
    public String toString() {
        return "The subject is : " + getSubject() + " Your Grade is: " + getGrade();
    }

    // standard getters and setters


    public GradeKey getId() {
        return id;
    }

    public void setId(GradeKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
