package com.calaton.student;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping(params = {"pageNo", "pageSize"})
    public ResponseEntity<Page<Student>> getAll(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(studentService.getAll(pageNo, pageSize));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        return ResponseEntity.ok(studentService.updateStudent(id, newStudent));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        return ResponseEntity.ok(studentService.createStudent(newStudent));
    }
}