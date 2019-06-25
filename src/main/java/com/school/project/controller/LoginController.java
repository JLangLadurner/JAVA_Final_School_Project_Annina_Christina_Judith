package com.school.project.controller;

import javax.validation.Valid;

import com.school.project.model.Student;
import com.school.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registrationstudent", method = RequestMethod.GET)
    public ModelAndView registration(){
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

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentService.findByEmail(auth.getName());
        modelAndView.addObject("firstName", "Welcome " + student.getFirstName() + " " + student.getLastName() + " (" + student.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Student Role");
        modelAndView.setViewName("/home");
        return modelAndView;
    }


}