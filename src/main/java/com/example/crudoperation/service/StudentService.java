package com.example.crudoperation.service;

import com.example.crudoperation.model.Student;
import com.example.crudoperation.repository.StudentRepository;

import org.springframework.stereotype.Service;
// import com.example.crudoperation.exception.IdNotFoundException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));
    }


    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAddress(student.getAddress());

        return studentRepository.save(existingStudent);
    }


    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}
