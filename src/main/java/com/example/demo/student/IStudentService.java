package com.example.demo.student;

import java.util.List;

import org.bson.types.ObjectId;

public interface IStudentService {
  List<Student> findAll();
  Object findById(String id);
  Student createStudent(Student student);
  Object deleteById(ObjectId id);
}
