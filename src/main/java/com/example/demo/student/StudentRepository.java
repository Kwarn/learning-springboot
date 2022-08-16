package com.example.demo.student;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
  public Optional<Student> findById(String id);
  public Object deleteById(ObjectId id);
}