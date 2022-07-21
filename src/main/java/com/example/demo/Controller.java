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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Controller implements CommandLineRunner {

  @Autowired
  private StudentRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Controller.class, args);
  }

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

  @GetMapping("/api/students")
  public List<Student> allStudents() {
    return repository.findAll();
  }

  @GetMapping("/api/students/id/{id}")
  public Object findStudentById(@PathVariable("id") String id) {
    Optional<Student> student = repository.findById(id);
    if (student != null) {
      return student;
    }
    return new Response(
      "Could not find a student with that email address.",
      HttpStatus.NOT_FOUND
    );
  }

  @PostMapping("/api/students")
  public Student createStudent(@Valid @RequestBody Student newStudent) {
    Student result = repository.save(newStudent);
    return result;
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();
    repository.save(new Student("Karl", "k@k.com", 21));
    repository.save(new Student("Bert", "b@b.com", 21));

    for (Student student : repository.findAll()) {
      logger.info("Found student: {}.", student);
    }
  }
}
