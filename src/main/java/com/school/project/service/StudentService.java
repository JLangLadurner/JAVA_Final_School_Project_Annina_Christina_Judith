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

@Service("userService")

public class StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(StudentRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student saveStudent(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setActive(1);
        Role userRole = roleRepository.findByRole("Student");
        student.setRoles(new HashSet<Role>(Arrays.asList(studentRole)));
        return studentRepository.save(student);
    }

}
