package com.example.crudoperation.controller;


import com.example.crudoperation.dto.StudentRequestDTO;
import com.example.crudoperation.dto.StudentResponseDTO;
// import com.example.crudoperation.model.Student;
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
  public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO request) {
       
        return new ResponseEntity<>(studentService.saveStudent(request), HttpStatus.CREATED);
    }


    @GetMapping("/read/{id}")
   public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
@GetMapping("/readAll")
public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAllStudents());
}

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
                                                 @RequestBody StudentRequestDTO request) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }
}
