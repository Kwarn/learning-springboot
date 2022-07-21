package com.example.demo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Student {

  @Id
  public String id;

  @NotBlank(message = "Missing 'name' field")
  @Getter
  @Setter
  private String name;

  @NotBlank(message = "Missing 'email' field")
  @Getter
  @Setter
  private String email;

  @NotNull(message = "Missing 'age' field")
  @Min(value = 16, message = "A minimum age of 16 is required")
  @Getter
  @Setter
  private Integer age;

  public Student(String name, String email, Integer age) {
    this.name = name;
    this.email = email;
    this.age = age;
  }

  @Override
  public String toString() {
    return (
      "Student{" +
      "id=" +
      id +
      ", name=" +
      name +
      ", email=" +
      email +
      ", age=" +
      age +
      "}"
    );
  }
}
