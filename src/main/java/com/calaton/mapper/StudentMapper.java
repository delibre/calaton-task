package com.calaton.mapper;

import com.calaton.model.entity.Address;
import com.calaton.model.entity.Student;
import com.calaton.model.request.StudentRequest;
import com.calaton.model.response.AddressResponse;
import com.calaton.model.response.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final AddressMapper addressMapper;

    public Student toEntity(StudentRequest studentRequest) {
        Address address = addressMapper.toEntity(studentRequest.getAddress());
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setFullName(studentRequest.getFullName());
        student.setAddress(address);
        return student;
    }

    public StudentResponse toResponse(Student student) {
        AddressResponse address = addressMapper.toResponse(student.getAddress());
        return StudentResponse.builder()
                .id(student.getId())
                .email(student.getEmail())
                .fullName(student.getFullName())
                .address(address)
                .build();
    }

    public void updateEntity(Student student, StudentRequest studentRequest) {
        student.setEmail(studentRequest.getEmail());
        student.setFullName(studentRequest.getFullName());
        addressMapper.updateEntity(student.getAddress(), studentRequest.getAddress());
    }
}
