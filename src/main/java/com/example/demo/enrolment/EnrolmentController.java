package com.example.demo.enrolment;

import javax.validation.Valid;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EnrolmentController {

  private IEnrolmentService enrolmentService;

  @PostMapping("/api/enroll")
  public Object enrollStudent(@Valid @RequestBody Enrolment enrolment)
    throws Exception {
    return enrolmentService.enroll(enrolment);
  }
}
