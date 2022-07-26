package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 

  Todo: 
    use Response convention not own implimentation
    "return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);""
*/

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
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
