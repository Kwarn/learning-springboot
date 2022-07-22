package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class Enrollee {

  @NotBlank(message = "Missing 'courseName' field")
  private final String courseName;

  @NotBlank(message = "Missing 'courseName' field")
  private final String studentId;
}
