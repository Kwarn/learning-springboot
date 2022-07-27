package com.example.demo.course;

import java.util.List;

import org.bson.types.ObjectId;

public interface ICourseService {
  List<Course> findAll();
  Object findById(String id);
  Course createCourse(Course course);
  Object deleteById(ObjectId id);
}
