package com.example.demo.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Student {

  @Id
  @Setter(AccessLevel.PROTECTED)
  public String id;

  @NotBlank(message = "Missing 'name' field")
  private final String name;

  @NotBlank(message = "Missing 'email' field")
  private final String email;

  @NotNull(message = "Missing 'age' field")
  @Min(value = 16, message = "A minimum age of 16 is required")
  private final Integer age;
}
