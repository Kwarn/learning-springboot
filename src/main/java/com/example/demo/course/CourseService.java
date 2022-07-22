package com.example.demo.course;

import java.util.List;
import java.util.Optional;
import com.example.demo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CourseService implements ICourseService, CommandLineRunner {

  @Autowired
  CourseRepository courseRepository;

  @Override
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  public Object findById(String id) {
    Optional<Course> course = courseRepository.findById(id);
    if (course != null) {
      return course;
    }
    return new Response(
      "Could not find a Course with that ID.",
      HttpStatus.NOT_FOUND,
      404
    );
  }

  @Override
  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public void run(String... args) throws Exception {
    courseRepository.deleteAll();

    Course course1 = new Course("Learn Java");
    Course course2 = new Course("Learn SpringBoot");
    courseRepository.save(course1);
    courseRepository.save(course2);

    for (Course course : courseRepository.findAll()) {
      // logger.info("Found course: {}.", course);
      System.out.println(course);
    }
  }
}
