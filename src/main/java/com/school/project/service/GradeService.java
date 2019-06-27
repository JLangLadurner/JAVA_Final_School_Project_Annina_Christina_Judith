package com.school.project.service;

import com.school.project.model.Role;
import com.school.project.model.Grade;
import com.school.project.repository.RoleRepository;
import com.school.project.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("gradeService")

public class GradeService {
    private GradeRepository gradeRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public GradeService(GradeRepository gradeRepository,
                        RoleRepository roleRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.gradeRepository = gradeRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Grade findByStudent(String grade) {
        return gradeRepository.findByStudent(grade);
    }

    @Transactional
    public void showAll(){
        List<Grade> gradeList = gradeRepository.findAll();
        gradeList.forEach(System.out::println);
    }


    }

