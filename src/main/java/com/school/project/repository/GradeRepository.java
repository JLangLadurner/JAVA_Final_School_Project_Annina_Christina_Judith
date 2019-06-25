package com.school.project.repository;

import com.school.project.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("gradeRepository")
public interface GradeRepository extends JpaRepository<Grade, Long>{

   Grade findByStudent(String grade);

}
