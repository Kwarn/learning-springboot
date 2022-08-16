package com.example.demo.enrolment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Enrolment {

  @NotBlank(message = "Missing 'courseId' field")
  private final String courseId;

  @NotBlank(message = "Missing 'studentId' field")
  private final String studentId;
}
