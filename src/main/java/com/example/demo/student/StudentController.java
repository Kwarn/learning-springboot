package com.example.demo.student;

import java.util.List;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StudentController {

  @Autowired
  StudentService studentService;

  @GetMapping("/api/students")
  public List<Student> allStudents() {
    return studentService.findAll();
  }

  @GetMapping("/api/student/{id}")
  public Object findStudentById(@PathVariable("id") String id) {
    return studentService.findById(id);
  }

  @PostMapping("/api/student")
  public Student createStudent(@Valid @RequestBody Student student) {
    return studentService.createStudent(student);
  }

  @DeleteMapping("/api/student/delete/{id}")
  public Object deleteStudent(@PathVariable("id") ObjectId id) {
    return studentService.deleteById(id);
  }
}
