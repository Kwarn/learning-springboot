package com.example.demo.course;

import java.util.List;

public interface ICourseService {
  List<Course> findAll();
  Object findById(String id);
  Course createCourse(Course course);
}
