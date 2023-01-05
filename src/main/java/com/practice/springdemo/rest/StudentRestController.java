package com.practice.springdemo.rest;

import com.practice.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" -  return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> theStudents = new ArrayList<>();
        theStudents.add(new Student("Mahima", "Mehta"));
        theStudents.add(new Student("Vaibhav", "Bavishi"));
        theStudents.add(new Student("Hardik", "Baid"));
        return  theStudents;
    }
}
