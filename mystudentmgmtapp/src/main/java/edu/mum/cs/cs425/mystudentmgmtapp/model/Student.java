package edu.mum.cs.cs425.mystudentmgmtapp.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Size(min = 4, max = 255)
    @Column(nullable = false, name = "student_number")
    private String studentNumber;

    @Size(min = 4, max = 255)
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Size(min = 4, max = 255)
    @Column(name = "middle_name")
    private String middleName;

    @Size(min = 4, max = 255)
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "cgpa")
    private double cgpa;

    private Date dateOfEnrollment;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Transcript> transcriptList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Classroom> classroomList;

    public Student() {
    }

    public Student(@Size(min = 4, max = 255) String studentNumber, @Size(min = 4, max = 255) String firstName, @Size(min = 4, max = 255) String middleName, @Size(min = 4, max = 255) String lastName, double cgpa, Date dateOfEnrollment) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cgpa = cgpa;
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public List<Transcript> getTranscriptList() {
        return transcriptList;
    }

    public void setTranscriptList(List<Transcript> transcriptList) {
        this.transcriptList = transcriptList;
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cgpa=" + cgpa +
                ", dateOfEnrollment=" + dateOfEnrollment +
                ", transcriptList=" + transcriptList +
                ", classroomList=" + classroomList +
                '}';
    }
}
