package com.example.demo;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class Course {

  @Id
  @Setter(AccessLevel.PROTECTED)
  private String id;

  @NotBlank(message = "Missing 'name' field")
  private final String courseName;

  private final List<Student> students;
}
