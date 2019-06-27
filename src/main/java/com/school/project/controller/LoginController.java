package com.school.project.controller;

import javax.validation.Valid;


import com.school.project.model.Student;
import com.school.project.repository.StudentRepository;
import com.school.project.service.StudentService;
import com.school.project.service.GradeService;
import com.school.project.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.school.project.model.Grade;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = "/registrationstudent", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Student student = new Student();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("registrationstudent");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationstudent", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Student student, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Student studentExists = studentService.findByEmail(student.getEmail());
        if (studentExists != null) {
            bindingResult
                    .rejectValue("email", "error.student",
                            "There is already a Student registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationstudent");
        } else {
            studentService.saveStudent(student);
            modelAndView.addObject("successMessage", "Student has been registered successfully");
            modelAndView.addObject("student", new Student());
            modelAndView.setViewName("registrationstudent");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentService.findByEmail(auth.getName());
        modelAndView.addObject("first_name", "Welcome " + student.getFirstName() + " " + student.getLastName() + " (" + student.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Student Role");

        if (student.getFkNewClassId() == 1) {
            String result = "";
            for (Student students : studentRepository.findAll()) {
                result +=
                        "<table>" + students.toString() + "</table>";

            }
            modelAndView.addObject("classmates",result);
        }

        modelAndView.addObject("adminMessage");
        modelAndView.setViewName("/home");
        return modelAndView;
        }



    /*@RequestMapping(value="/student/gradelist", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Grade agrade = gradeService.findByStudent(auth.getName());
        Student student = studentService.findByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + student.getFirstName() + " " + student.getLastName() + " (" + student.getEmail() + ")");

        String result = "";

            for(Grade grade : gradeRepository.findAll()){

                    result += "<div>" + grade.toString() + "</div>";
            }
            modelAndView.addObject("adminMessage",result);

        modelAndView.setViewName("/student/gradelist");
        return modelAndView;
    }*/

    }

