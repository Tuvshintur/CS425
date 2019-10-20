package mum.edu.cs.cs425.eregistrar.service;

import mum.edu.cs.cs425.eregistrar.model.Student;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {

    public Iterable<Student> getAllStudents();
    public Page<Student> getAllStudentsPaged(int pageNo);
    public Student saveStudent(Student student);
    public Student getStudentById(Integer studentId);
    public void deleteStudentById(Integer studentId);
    public Optional<Student> findStudentByStudentNumber(String isbn);

}
