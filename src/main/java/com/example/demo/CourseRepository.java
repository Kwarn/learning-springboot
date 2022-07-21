package com.example.demo;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
  public Course findByCourseName(String courseName);
  public Optional<Course> findById(String id);
}