package com.Mapping.ManyToMany.Repository;

import com.Mapping.ManyToMany.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {
}
