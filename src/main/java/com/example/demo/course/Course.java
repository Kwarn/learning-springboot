package com.example.demo.course;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Course {

  @Id
  @Setter(AccessLevel.PROTECTED)
  private String id;

  @NotBlank(message = "Missing 'name' field")
  private final String courseName;

  // list of studentId's
  private List<String> students;
}
