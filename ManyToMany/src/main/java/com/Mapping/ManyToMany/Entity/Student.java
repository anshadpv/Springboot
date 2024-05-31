package com.Mapping.ManyToMany.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Table(name = "student_table")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Student_Course_Table",
        joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")
        },
            inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")
            })
    private Set<Courses> courses;
}
