package com.school.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class GradeKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "subjectId")
    Long subjectId;

    public GradeKey(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public GradeKey() {};

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

//We need to provide an implementation of the hashcode() and equals() methods
//None of the fields can be an entity themselves resource: https://www.baeldung.com/jpa-many-to-many
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeKey)) return false;
        GradeKey gradeKey = (GradeKey) o;
        return Objects.equals(getStudentId(), gradeKey.getStudentId()) &&
                Objects.equals(getSubjectId(), gradeKey.getSubjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getSubjectId());
    }
}
