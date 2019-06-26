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

    @Column(name = "firstName")
    @NotEmpty(message = "*Please provide your first name")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "age")
    @NotEmpty(message = "*Please provide your age")
    private String age;

    @Column(name = "oldClass")
    @NotEmpty(message = "*Please provide your last Class")
    private String oldClass;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "active")
    private int active;

    @Column(name = "newClass")
    private int newClass;


    @Column(name = "status")
    private int status;

   /* @Column(name = "fk_parentId")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent", joinColumns = @JoinColumn(name = "parentId"), inverseJoinColumns = @JoinColumn(name = "fk_parentId"))
    private Set<Parent> parent;*/

    @OneToMany(mappedBy = "student")
    Set<Grade> grade;

   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "<list>Your Classmates are:  <tr><td>" + firstName + "</td>" +
                                                "<td>"+lastName+ "</td></tr>";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOldClass() {
        return oldClass;
    }

    public void setOldClass(String oldClass) {
        this.oldClass = oldClass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getNewClass() {
        return newClass;
    }

    public void setNewClass(int newClass) {
        this.newClass = newClass;
    }

    public Set<Grade> getGrade() {
        return grade;
    }

    public void setGrade(Set<Grade> grade) {
        this.grade = grade;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
