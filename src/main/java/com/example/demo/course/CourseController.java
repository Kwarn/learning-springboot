package com.example.demo.course;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CourseController {

  @Autowired
  CourseService courseService;

  @GetMapping("/api/courses")
  public List<Course> allCourses() {
    return courseService.findAll();
  }

  @GetMapping("/api/course/{id}")
  public Object findCourseById(@PathVariable("id") String id) {
    return courseService.findById(id);
  }

  @PostMapping("/api/course")
  public Course createCourse(@Valid @RequestBody Course course) {
    return courseService.createCourse(course);
  }
}
