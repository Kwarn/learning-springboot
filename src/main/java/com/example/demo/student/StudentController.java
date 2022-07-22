package com.example.demo.student;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class StudentController {

  private IStudentService studentService;

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
}
