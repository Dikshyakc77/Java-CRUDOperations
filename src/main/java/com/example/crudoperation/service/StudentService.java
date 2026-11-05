package com.example.crudoperation.service;

import com.example.crudoperation.dto.StudentRequestDTO;
import com.example.crudoperation.dto.StudentResponseDTO;
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


    public StudentResponseDTO saveStudent(StudentRequestDTO request) {
        Student student = new Student(request.name(), request.email(), request.address());
        return toResponse(studentRepository.save(student));
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponseDTO getStudentById(Long id) {
        return toResponse(findStudent(id));
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        Student existing = findStudent(id);

        existing.setName(request.name());
        existing.setEmail(request.email());
        existing.setAddress(request.address());

        return toResponse(studentRepository.save(existing));
    }

    public void deleteStudent(Long id) {
        findStudent(id);
        studentRepository.deleteById(id);
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    private StudentResponseDTO toResponse(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAddress()
        );
    }
}