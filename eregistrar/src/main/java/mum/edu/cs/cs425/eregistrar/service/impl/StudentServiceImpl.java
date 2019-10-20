package mum.edu.cs.cs425.eregistrar.service.impl;

import mum.edu.cs.cs425.eregistrar.model.Student;
import mum.edu.cs.cs425.eregistrar.repository.StudentRepository;
import mum.edu.cs.cs425.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Iterable<Student> getAllStudents() {
        return repository.findAll(Sort.by("studentNumber"));
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return repository.findById(studentId).orElse(null);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public Optional<Student> findStudentByStudentNumber(String studentNumber) {
        return repository.findStudentByStudentNumber(studentNumber);
    }

}
