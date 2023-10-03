package com.calaton.service;

import com.calaton.mapper.StudentMapper;
import com.calaton.model.entity.Student;
import com.calaton.model.request.StudentRequest;
import com.calaton.model.response.StudentResponse;
import com.calaton.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Transactional
    public StudentResponse createStudent(StudentRequest studentRequest) {
        log.info("[createStudent] invoked with studentRequest=[{}]", studentRequest);
        Student student = studentMapper.toEntity(studentRequest);
        student = studentRepository.save(student);
        return studentMapper.toResponse(student);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        log.info("[getStudentById] invoked with id=[{}]", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return studentMapper.toResponse(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        log.info("[getAllStudents] invoked with pageable=[{}]", pageable);
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(studentMapper::toResponse);
    }

    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        log.info("[updateStudent] invoked with id=[{}], studentRequest=[{}]", id, studentRequest);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        studentMapper.updateEntity(student, studentRequest);
        return studentMapper.toResponse(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        log.info("[deleteStudent] invoked with id=[{}]", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        studentRepository.delete(student);
    }
}
