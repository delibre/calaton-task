package com.calaton.student;

import com.calaton.address.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    public Page<Student> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepository.findAll(pageable);
    }

    public Student updateStudent(Long id, Student newStudent) {
        Student oldStudent = studentRepository.getStudentById(id);

        oldStudent.setFullName(newStudent.getFullName());
        oldStudent.setEmail(newStudent.getEmail());

        Address newAddress = newStudent.getAddress();
        Address oldAddress = oldStudent.getAddress();
        oldAddress.setCity(newAddress.getCity());
        oldAddress.setStreet(newAddress.getStreet());

        return studentRepository.save(oldStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            return;
        }
        studentRepository.deleteStudentById(id);
    }

    public Student createStudent(Student newStudent) {

        if (newStudent.getAddress() != null) {
            newStudent.getAddress().setStudent(newStudent);
        }

        return studentRepository.save(newStudent);
    }
}
