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
@Table(name = "subKey")

public class SubKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subKeyId")
    private int id;

    @Column(name = "fk_teacherId")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher", joinColumns = @JoinColumn(name = "fk_teacherId"), inverseJoinColumns = @JoinColumn(name = "teacherId"))
    private Set<Parent> parent;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject", joinColumns = @JoinColumn(name = "fk_subjectId"), inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private Set<Role> roles;
}
