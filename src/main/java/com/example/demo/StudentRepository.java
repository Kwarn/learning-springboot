package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

  public Student findByName(String name);
  public List<Student> findByEmail(String email);

}