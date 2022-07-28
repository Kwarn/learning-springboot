package com.example.demo.course;

import com.example.demo.Response;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

  public Object deleteById(ObjectId id) {
    return courseRepository.deleteById(id);
  }

  public void run(String... args) throws Exception {
    courseRepository.deleteAll();

    Course course1 = new Course(
      "Learn Java",
      "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible."
    );
    Course course2 = new Course(
      "Learn SpringBoot",
      "The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform."
    );
    Course course3 = new Course(
      "Learn React",
      "React is a free and open-source front-end JavaScript library for building user interfaces based on UI components. It is maintained by Meta and a community of individual developers and companies."
    );
    Course course4 = new Course(
      "Learn Angular",
      "Angular is a TypeScript-based free and open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations. Angular is a complete rewrite from the same team that built AngularJS"
    );
    courseRepository.save(course1);
    courseRepository.save(course2);
    courseRepository.save(course3);
    courseRepository.save(course4);

    for (Course course : courseRepository.findAll()) {
      // logger.info("Found course: {}.", course);
      System.out.println(course);
    }
  }
}
