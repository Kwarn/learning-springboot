package com.example.demo.enrolment;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EnrolmentController {

  @Autowired
  EnrolmentService enrolmentService;

  @PostMapping("/api/enroll")
  public Object enrollStudent(@Valid @RequestBody Enrolment enrolment)
    throws Exception {
    return enrolmentService.enroll(enrolment);
  }
}
