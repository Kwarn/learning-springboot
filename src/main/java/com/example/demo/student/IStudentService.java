package com.example.demo.student;

import java.util.List;

public interface IStudentService {
  List<Student> findAll();
  Object findById(String id);
  Student createStudent(Student student);
}
