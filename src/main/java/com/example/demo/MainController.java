package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/* 

  Todo: 
    figure out how to pass a single repo instance between files to avoid
    "The bean 'courseRepository' could not be registered. A bean with that name has already been defined"
*/

@RestController
@SpringBootApplication
@EnableMongoRepositories
public class MainController {

  Logger logger = LoggerFactory.getLogger(MainController.class);

  @RequestMapping("/")
  public String index() {
    logger.trace("A TRACE Message");
    logger.debug("A DEBUG Message");
    logger.info("An INFO Message");
    logger.warn("A WARN Message");
    logger.error("An ERROR Message");

    return "Howdy! Check out the Logs to see the output...";
  }

  public static void main(String[] args) {
    SpringApplication.run(MainController.class, args);
  }
}
