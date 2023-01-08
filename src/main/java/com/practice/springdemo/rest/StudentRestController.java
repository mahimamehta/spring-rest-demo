package com.practice.springdemo.rest;

import com.practice.springdemo.entity.Student;
import com.practice.springdemo.exceptions.StudentErrorResponse;
import com.practice.springdemo.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data only once
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Mahima", "Mehta"));
        theStudents.add(new Student("Vaibhav", "Bavishi"));
        theStudents.add(new Student("Hardik", "Baid"));
    }

    // define endpoint for "/students" -  return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return  theStudents;
    }

    // define endpoint for "/students/{studentId}" -  return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId){

        // just check the studentId with list size
        if(studentId <0 || studentId >= theStudents.size())
            throw new StudentNotFoundException("Student id not found = "+ studentId);

        return  theStudents.get(studentId);
    }

    // define a method to handle the exception using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // define a method to handle the any exception using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
