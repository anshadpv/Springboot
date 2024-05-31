package com.Mapping.ManyToMany.Controller;

import com.Mapping.ManyToMany.Entity.Student;
import com.Mapping.ManyToMany.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentRepo.findAll();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentRepo.save(student);
        return ResponseEntity.ok("Student Added.");
    }

    @DeleteMapping("/delete")
    public void deleteAllStudents(){
        studentRepo.deleteAll();
    }

}
