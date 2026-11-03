package com.example.crudoperation.controller;


import com.example.crudoperation.model.Student;
import com.example.crudoperation.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
// import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = studentService.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @GetMapping("/read/{id}")
   public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
@GetMapping("/readAll")
public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAllStudents());
}

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // DELETE -> DELETE http://localhost:8080/students/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }
}
