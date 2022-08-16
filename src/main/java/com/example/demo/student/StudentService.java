package com.example.demo.student;

import java.util.List;
import java.util.Optional;
import com.example.demo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StudentService implements IStudentService, CommandLineRunner {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Object findById(String id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student != null) {
      return student;
    }
    return new Response(
      "Could not find a student with that ID.",
      HttpStatus.NOT_FOUND,
      404
    );
  }

  @Override
  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public Object deleteById(ObjectId id) {
    return studentRepository.deleteById(id);
  }

  public void run(String... args) throws Exception {
    studentRepository.deleteAll();

    Student student1 = new Student("Karl", "k@k.com", 34);
    Student student2 = new Student("Bert", "b@b.com", 22);
    studentRepository.save(student1);
    studentRepository.save(student2);

    for (Student student : studentRepository.findAll()) {
      // logger.info("Found student: {}.", student);
      System.out.println(student);
    }
  }
}
