package com.school.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studentId")
    private int id;

    @Column(name = "stud_firstName")
    @NotEmpty(message = "*Please provide your first name")
    private String stud_firstName;

    @Column(name = "stud_lastName")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "stud_age")
    @NotEmpty(message = "*Please provide your age")
    private int stud_age;

    @Column(name = "stud_oldClass")
    @NotEmpty(message = "*Please provide your last Class")
    private String stud_oldClass;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "active")
    private int active;

    @Column(name = "status")
    private int status;

    @Column(name = "fk_parentId")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent", joinColumns = @JoinColumn(name = "fk_parentId"), inverseJoinColumns = @JoinColumn(name = "parentId"))
    private Set<Parent> parent;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
