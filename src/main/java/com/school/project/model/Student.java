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
    private String stud_lastName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String stud_email;

    @Column(name = "stud_age")
    @NotEmpty(message = "*Please provide your age")
    private int stud_age;

    @Column(name = "stud_oldClass")
    @NotEmpty(message = "*Please provide your last Class")
    private String stud_oldClass;

    @Column(name = "stud_password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String stud_password;

    @Column(name = "active")
    private int active;

    @Column(name = "status")
    private int status;

   /* @Column(name = "fk_parentId")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent", joinColumns = @JoinColumn(name = "parentId"), inverseJoinColumns = @JoinColumn(name = "fk_parentId"))
    private Set<Parent> parent;*/

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStud_firstName() {
        return stud_firstName;
    }

    public void setStud_firstName(String stud_firstName) {
        this.stud_firstName = stud_firstName;
    }

    public String getStud_lastName() {
        return stud_lastName;
    }

    public void setStud_lastName(String stud_lastName) {
        this.stud_lastName = stud_lastName;
    }

    public String getStud_email() {
        return stud_email;
    }

    public void setStud_email(String stud_email) {
        this.stud_email = stud_email;
    }

    public int getStud_age() {
        return stud_age;
    }

    public void setStud_age(int stud_age) {
        this.stud_age = stud_age;
    }

    public String getStud_oldClass() {
        return stud_oldClass;
    }

    public void setStud_oldClass(String stud_oldClass) {
        this.stud_oldClass = stud_oldClass;
    }

    public String getStud_password() {
        return stud_password;
    }

    public void setStud_password(String stud_password) {
        this.stud_password = stud_password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
