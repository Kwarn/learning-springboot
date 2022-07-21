package com.example.demo;

import java.util.List;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class Course {
  @Id
  private String id;

  @Getter
  @Setter
  @NotBlank(message = "Missing 'name' field")
  private String courseName;

  @Getter
  @Setter
  private List<Student> students;

  public Course(String courseName, List<Student> students) {
    this.courseName = courseName;
    this.students = students;
  }
}
