package com.example.demo;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  todo:
    OOP concepts:
      add student to course, add course to student
      inheritance refresher - create use case

    practice some java loops (& performance)
    List vs Array
    add more specific error handling

*/

@SpringBootApplication
@RestController
public class Controller implements CommandLineRunner {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseRepository courseRepository;

  public static void main(String[] args) {
    SpringApplication.run(Controller.class, args);
  }

  /*  Logging  */

  Logger logger = LoggerFactory.getLogger(Controller.class);

  @RequestMapping("/")
  public String index() {
    logger.trace("A TRACE Message");
    logger.debug("A DEBUG Message");
    logger.info("An INFO Message");
    logger.warn("A WARN Message");
    logger.error("An ERROR Message");

    return "Howdy! Check out the Logs to see the output...";
  }

  /*  Students  */

  @GetMapping("/api/students")
  public List<Student> allStudents() {
    return studentRepository.findAll();
  }

  @GetMapping("/api/students/{id}")
  public Object findStudentById(@PathVariable("id") String id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student != null) {
      return student;
    }
    return new Response(
      "Could not find a student with that ID.",
      HttpStatus.NOT_FOUND
    );
  }

  @PostMapping("/api/students")
  public Student createStudent(@Valid @RequestBody Student newStudent) {
    Student result = studentRepository.save(newStudent);
    return result;
  }

  /*  Courses  */

  @GetMapping("/api/courses")
  public List<Course> allCourses(@Valid @RequestBody Enrollee enrollee) {
    List<Course> courses = courseRepository.findAll();
    return courses;
  }

  @GetMapping("/api/courses/{id}")
  public Object findCourseById(@PathVariable("id") String id) {
    Optional<Course> course = courseRepository.findById(id);
    if (course != null) {
      return course;
    }
    return new Response(
      "Could not find a course with that ID.",
      HttpStatus.NOT_FOUND
    );
  }

  @PostMapping("/api/courses")
  public Course createCourse(@Valid @RequestBody Course newCourse) {
    Course result = courseRepository.save(newCourse);
    return result;
  }

  /*  Enrolling  */

  @PostMapping("/api/enroll")
  public Course enrollStudent(@Valid @RequestBody Enrollee enrollee) {
    // WIP
    // {corseName: "Learn SpringBoot", studentId: "{id}"}
    Course course = courseRepository.findByCourseName(enrollee.getCourseName());
    return course;
  }

  @Override
  public void run(String... args) throws Exception {
    studentRepository.deleteAll();
    studentRepository.save(new Student("Karl", "k@k.com", 21));
    studentRepository.save(new Student("Bert", "b@b.com", 21));

    for (Student student : studentRepository.findAll()) {
      logger.info("Found student: {}.", student);
    }
  }
}
