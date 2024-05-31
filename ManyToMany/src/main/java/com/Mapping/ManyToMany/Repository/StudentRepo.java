package com.Mapping.ManyToMany.Repository;

import com.Mapping.ManyToMany.Entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
