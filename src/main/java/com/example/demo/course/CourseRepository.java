package com.example.demo.course;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
  public List<Course> findByName(String name);

  public Optional<Course> findById(String id);

  public Object deleteById(ObjectId id);
}
