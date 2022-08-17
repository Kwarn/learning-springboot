package com.example.demo.enrolment;

import com.example.demo.Response;
import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EnrolmentService implements IEnrolmentService {

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  StudentRepository studentRepository;

  private static List<String> addStringToListSafe(
    List<String> list,
    String string
  )
    throws Exception {
    if (string.length() < 1) {
      throw new Exception("no string was passed to addStringToListSafe()");
    }
    if (list == null) {
      List<String> newList = Arrays.asList(string);
      return newList;
    }
    list.add(string);
    return list;
  }

  @Override
  public Object enroll(Enrolment enroll) throws Exception {
    String courseId = enroll.getCourseId();
    Optional<Course> courseResult = courseRepository.findById(courseId);
    Course course;

    if (courseResult.isPresent() == false) {
      return new Response(
        "Could not find a course with that id.",
        HttpStatus.NOT_FOUND,
        404
      );
    }
    course = courseResult.get(); // uggo

    String studentId = enroll.getStudentId();
    Optional<Student> student = studentRepository.findById(studentId);

    if (student.isPresent() == false) {
      return new Response(
        "Could not find a student with that ID.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    List<String> studentIds = course.getStudents(); // no error

    if (studentIds != null) {
      boolean isAlreadyEnrolled = course
        .getStudents()
        .stream()
        .anyMatch(id -> id.equals(studentId));

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
      enroll.getStudentId()
    );
    course.setStudents(updatedStudentIds);
    Course updatedCourse = courseRepository.save(course);
    return updatedCourse;
  }

  public Object unenroll(Enrolment enroll) throws Exception {
    String courseId = enroll.getCourseId();
    Optional<Course> courseResult = courseRepository.findById(courseId);
    Course course;

    if (courseResult.isPresent() == false) {
      return new Response(
        "Could not find a course with that id.",
        HttpStatus.NOT_FOUND,
        404
      );
    }
    course = courseResult.get(); // uggo

    String studentId = enroll.getStudentId();
    Optional<Student> student = studentRepository.findById(studentId);

    if (student.isPresent() == false) {
      return new Response(
        "Could not find a student with that ID.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    List<String> studentIds = course.getStudents(); // no error

    if (studentIds != null) {
      boolean isAlreadyEnrolled = course
        .getStudents()
        .stream()
        .anyMatch(id -> id.equals(studentId));

      if (!isAlreadyEnrolled) {
        return new Response(
          "The student is not enrolled in this course.",
          HttpStatus.CONFLICT,
          409
        );
      }
    }

    List<String> updatedStudentIds = studentIds
      .stream()
      .filter(id -> !id.equals(enroll.getStudentId()))
      .collect(Collectors.toList());

    course.setStudents(updatedStudentIds);
    Course updatedCourse = courseRepository.save(course);
    return updatedCourse;
  }
}
