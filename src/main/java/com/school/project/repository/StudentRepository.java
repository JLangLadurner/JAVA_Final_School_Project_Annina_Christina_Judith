package com.school.project.repository;

import com.school.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")

public interface StudentRepository extends JpaRepository<Student, Long>{
    Student findStudentByEmail(String stud_email);
}
