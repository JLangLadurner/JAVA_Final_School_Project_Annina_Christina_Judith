package com.school.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")


public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subjectId")
    private int id;
    @Column(name = "subjectName")
    private String subjectName;

    //mapped via GradeKey
    @OneToMany(mappedBy = "subject")
    Set<Grade> grade;
}
