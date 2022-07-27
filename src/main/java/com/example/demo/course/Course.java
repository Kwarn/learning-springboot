package com.example.demo.course;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
  private final String name;

  @NotBlank(message = "Missing 'description' field")
  private final String description;

  // list of studentId's
  private List<String> students;
}
