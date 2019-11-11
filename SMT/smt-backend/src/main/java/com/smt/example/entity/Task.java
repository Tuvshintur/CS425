package com.smt.example.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smt.example.dto.IGeneralDTO;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Task Entity @author Davaa
 */

@Entity
@Table(name = "tasks")
public class Task implements IGeneralDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;
    private String name;
    private int priority;
    private int developmentEffortPoint;
    private int testEffortPoint;
    private TaskStatus status = TaskStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private User developer;

    @ManyToOne
    @JoinColumn(name = "tester_id", nullable = false)
    private User tester;

    @NotNull(message = "*Start date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "*Due date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "created_user_id", nullable = false)
    private User createdUser;

    @NotNull(message = "*Created date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<StatusChangeRecord> statusChangeRecords;

    public Task() {
    }

    public Task(String name, int priority, int developmentEffortPoint, int testEffortPoint, TaskStatus status, User developer, User tester, @NotNull(message = "*Start date is required.") LocalDate startDate, @NotNull(message = "*Due date is required.") LocalDate dueDate, User createdUser, @NotNull(message = "*Created date is required.") LocalDate createdDate, Project project, Sprint sprint) {
        this.name = name;
        this.priority = priority;
        this.developmentEffortPoint = developmentEffortPoint;
        this.testEffortPoint = testEffortPoint;
        this.status = status;
        this.developer = developer;
        this.tester = tester;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
        this.project = project;
        this.sprint = sprint;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDevelopmentEffortPoint() {
        return developmentEffortPoint;
    }

    public void setDevelopmentEffortPoint(int developmentEffortPoint) {
        this.developmentEffortPoint = developmentEffortPoint;
    }

    public int getTestEffortPoint() {
        return testEffortPoint;
    }

    public void setTestEffortPoint(int testEffortPoint) {
        this.testEffortPoint = testEffortPoint;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public User getTester() {
        return tester;
    }

    public void setTester(User tester) {
        this.tester = tester;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Sprint getSprint() {
        return sprint != null ? this.sprint :(this.sprint = new Sprint());
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public List<StatusChangeRecord> getStatusChangeRecords() {
        return statusChangeRecords;
    }

    public void setStatusChangeRecords(List<StatusChangeRecord> statusChangeRecords) {
        this.statusChangeRecords = statusChangeRecords;
    }
}
