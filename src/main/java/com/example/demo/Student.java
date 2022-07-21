package com.example.demo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Student {
    @Id
    public String id;
    @NotBlank(message = "Missing 'name' field")
    private String name;
    @NotBlank(message = "Missing 'email' field")
    private String email;
    @NotNull(message = "Missing 'age' field")
    @Min(value=16, message="A minimum age of 16 is required")
    private Integer age;

    public Student(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", age=" + age +
                "}";
    }
}
