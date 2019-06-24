package com.school.project.service;

import com.school.project.model.Role;
import com.school.project.model.Student;
import com.school.project.repository.RoleRepository;
import com.school.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("studentService")

public class StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Student findStudentByEmail(String stud_email) {
        return studentRepository.findStudentByEmail(stud_email);
    }

    public Student saveStudent(Student student) {
        student.setStud_password(bCryptPasswordEncoder.encode(student.getStud_password()));
        student.setActive(1);
        Role studentRole = roleRepository.findByRole("Student");
        student.setRoles(new HashSet<Role>(Arrays.asList(studentRole)));
        return studentRepository.save(student);
    }



}
