package com.example.demo;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class Enroll {

  @Getter
  @Setter
  @NotBlank(message = "Missing 'courseName' field")
  private String courseName;

  @Getter
  @Setter
  @NotBlank(message = "Missing 'courseName' field")
  private String studentId;

  public Enroll(String courseName, String studentId) {
    this.courseName = courseName;
    this.studentId = studentId;
  }
}
