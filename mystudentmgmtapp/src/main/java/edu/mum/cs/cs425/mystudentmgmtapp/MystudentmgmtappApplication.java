package edu.mum.cs.cs425.mystudentmgmtapp;

import edu.mum.cs.cs425.mystudentmgmtapp.Repository.StudentRepository;
import edu.mum.cs.cs425.mystudentmgmtapp.model.Classroom;
import edu.mum.cs.cs425.mystudentmgmtapp.model.Student;
import edu.mum.cs.cs425.mystudentmgmtapp.model.Transcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MystudentmgmtappApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(MystudentmgmtappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello ELibraryApp!!!");
        Student student = new Student("000-61-0001", "Anna", "Lynn", "Smith", 3.45, new Date());

        Student savedStudent = studentRepository.save(student);

        System.out.println(savedStudent);

        List<Transcript> list = new ArrayList<Transcript>();

        list.add(new Transcript("BS Computer Science", savedStudent));

        savedStudent.setTranscriptList(list);

        List<Classroom> list1 = new ArrayList<Classroom>();
        list1.add(new Classroom("McLaughlin building", "M105", savedStudent));
        list1.add(new Classroom("McLaughlin building", "M106", savedStudent));
        list1.add(new Classroom("McLaughlin building", "M107", savedStudent));

        savedStudent.setClassroomList(list1);

        studentRepository.save(savedStudent);
    }

}
