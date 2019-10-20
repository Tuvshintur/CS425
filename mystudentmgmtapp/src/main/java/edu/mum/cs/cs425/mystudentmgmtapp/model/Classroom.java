package edu.mum.cs.cs425.mystudentmgmtapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="classroom_id")
    private long classroomId;

    @Column(name="building_name", nullable = false)
    private String buildingName;

    @Column(name="room_number", nullable = false)
    private String roomNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Student student;

    public Classroom() {
    }

    public Classroom(String buildingName, String roomNumber, Student student) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.student = student;
    }

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(long classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId=" + classroomId +
                ", buildingName='" + buildingName + '\'' +
                ", roomNumber='" + roomNumber +
                '}';
    }
}
