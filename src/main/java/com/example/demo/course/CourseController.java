package com.example.demo.course;

import java.util.List;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

  @DeleteMapping("/api/course/{id}")
  public Object deleteCourse(@PathVariable("id") ObjectId id) {
    return courseService.deleteById(id);
  }
}
