package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/hello")
  public String hello(
    @RequestParam(value = "name", defaultValue = "World") String name
  ) {
    return String.format("Hello %s!", name);
  }

  @GetMapping("/students")
  public List<Student> allStudents() {
    return repository.findAll();
  }

  @GetMapping("/findStudent")
  public Object findStudentByEmail(@Valid @RequestBody String email)
    throws Exception {
    ObjectMapper mapper = new JsonMapper();
    JsonNode json = mapper.readTree(email);
    String e = json.get("email").asText();

    List<Student> students = repository.findByEmail(e);
    if (students.size() >= 1) {
      return students.get(0);
    }
    return new Response(
      "Could not find a student with that email address.",
      HttpStatus.NOT_FOUND
    );
  }

  @PostMapping("/createStudent")
  public Student createStudent(@Valid @RequestBody Student newStudent) {
    Student result = repository.save(newStudent);
    return result;
  }

  // @PostMapping('/enrollStudent')
  // public Course enrollStudent(@Valid @RequestBody newStudent)

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();
    repository.save(new Student("Karl", "k@k.com", 21));
    repository.save(new Student("Bert", "b@b.com", 21));

    for (Student student : repository.findAll()) {
      logger.info("Found student: {}.", student);
    }

    logger.info(
      "Student found with findByName('Karl'): {}",
      repository.findByName("Karl")
    );

    for (Student student : repository.findByEmail("b@b.com")) {
      logger.info("Student found with findByEmail('b@b.com'): {}", student);
    }
  }
}
