package com.example.demo;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class Course {

  @Getter
  @Setter
  @NotBlank(message = "Missing 'name' field")
  private String name;

  @Getter
  @Setter
  private List<Student> students;

  public Course(String name, List<Student> students) {
    this.name = name;
    this.students = students;
  }
}
