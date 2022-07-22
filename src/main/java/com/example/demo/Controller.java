package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@RestController
@SpringBootApplication
public class Controller implements CommandLineRunner {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CourseRepository courseRepository;

  public static void main(String[] args) {
    SpringApplication.run(Controller.class, args);
  }

  private static List<String> addStringToListSafe(
    List<String> list,
    String string
  )
    throws Exception {
    if (string.length() < 1) {
      throw new Exception("no string was passed to addStringToListSafe()");
    }
    if (list == null) {
      List<String> updatedList = Arrays.asList(string);
      return updatedList;
    } else {
      // does this mutate the original list?
      list.add(string);
      return list;
    }
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

  @GetMapping("/api/student/{id}")
  public Object findStudentById(@PathVariable("id") String id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student != null) {
      return student;
    }
    return new Response(
      "Could not find a student with that ID.",
      HttpStatus.NOT_FOUND,
      404
    );
  }

  @PostMapping("/api/student")
  public Student createStudent(@Valid @RequestBody Student newStudent) {
    return studentRepository.save(newStudent);
  }

  /*  Courses  */

  @GetMapping("/api/courses")
  public List<Course> allCourses() {
    List<Course> courses = courseRepository.findAll();
    return courses;
  }

  @GetMapping("/api/course/{id}")
  public Object findCourseById(@PathVariable("id") String id) {
    Optional<Course> course = courseRepository.findById(id);
    if (course != null) {
      return course;
    }
    return new Response(
      "Could not find a course with that ID.",
      HttpStatus.NOT_FOUND,
      404
    );
  }

  @PostMapping("/api/course")
  public Course createCourse(@Valid @RequestBody Course newCourse) {
    return courseRepository.save(newCourse);
  }

  /*  Enrolling  */

  @PostMapping("/api/enroll")
  public Object enrollStudent(@Valid @RequestBody Enrollee enrollee)
    throws Exception {
    List<Course> courses = courseRepository.findByCourseName(
      enrollee.getCourseName()
    );

    if (courses.size() < 1) {
      return new Response(
        "Could not find a course with that name.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    String studentId = enrollee.getStudentId();
    Optional<Student> student = studentRepository.findById(studentId);

    if (student == null) {
      return new Response(
        "Could not find a student with that ID.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    Course course = courses.get(0);
    List<String> studentIds = course.getStudents();

    if (studentIds != null) {
      boolean isAlreadyEnrolled = course
        .getStudents()
        .stream()
        .anyMatch(id -> id == studentId);

      if (isAlreadyEnrolled) {
        return new Response(
          "The student is already enrolled in this course.",
          HttpStatus.CONFLICT,
          409
        );
      }
    }
    List<String> updatedStudentIds = addStringToListSafe(
      studentIds,
      enrollee.getStudentId()
    );
    course.setStudents(updatedStudentIds);
    Course updatedCourse = courseRepository.save(course);
    return updatedCourse;
  }

  @Override
  public void run(String... args) throws Exception {
    studentRepository.deleteAll();
    courseRepository.deleteAll();

    Student student1 = new Student("Karl", "k@k.com", 34);
    Student student2 = new Student("Bert", "b@b.com", 22);
    studentRepository.save(student1);
    studentRepository.save(student2);

    Course course1 = new Course("Learn Java");
    Course course2 = new Course("Learn SpringBoot");

    courseRepository.save(course1);
    courseRepository.save(course2);

    for (Student student : studentRepository.findAll()) {
      logger.info("Found student: {}.", student);
    }
    for (Course course : courseRepository.findAll()) {
      logger.info("Found course: {}.", course);
    }
  }
}
