package com.example.demo.enrolment;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import com.example.demo.Response;
import com.example.demo.course.Course;
import com.example.demo.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.course.CourseRepository;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
  public Object enroll(Enrolment enroll) throws Exception{
    List<Course> courses = courseRepository.findByCourseName(
      enroll.getCourseName()
    );

    if (courses.size() < 1) {
      return new Response(
        "Could not find a course with that name.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    String studentId = enroll.getStudentId();
    Optional<Student> student = studentRepository.findById(studentId);

    if (student.isPresent() == false) {
      return new Response(
        "Could not find a student with that ID.",
        HttpStatus.NOT_FOUND,
        404
      );
    }

    Course course = courses.get(0);
    List<String> studentIds = course.getStudents();

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
}
