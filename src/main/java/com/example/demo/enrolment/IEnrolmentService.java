package com.example.demo.enrolment;

public interface IEnrolmentService {
  Object enroll(Enrolment enroll) throws Exception;
  Object unenroll(Enrolment enroll) throws Exception;
}
