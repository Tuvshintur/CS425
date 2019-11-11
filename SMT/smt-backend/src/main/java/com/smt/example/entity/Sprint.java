/**
 * Spring Entity @author Davaa
 */
package com.smt.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smt.example.dto.IGeneralDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sprints")
public class Sprint implements IGeneralDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprintId")
    private Long sprintId;

    private String name;
    private String description;

    @NotNull(message = "*Sprint phase is required.")
    private SprintPhase sprintPhase;

    private int effortPlanned;
    private int effortBurnt;

    public int getEffortPlanned() {
        return effortPlanned;
    }

    public void setEffortPlanned(int effortPlanned) {
        this.effortPlanned = effortPlanned;
    }

    public int getEffortBurnt() {
        return effortBurnt;
    }

    public void setEffortBurnt(int effortBurnt) {
        this.effortBurnt = effortBurnt;
    }

    @NotNull(message = "*Sprint status is required.")
    private SprintStatus sprintStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks;


    public Sprint() {
    }

    public Sprint(String name, String description, @NotNull(message = "*Sprint phase is required.") SprintPhase sprintPhase, int effortPlanned, int effortBurnt, @NotNull(message = "*Sprint status is required.") SprintStatus sprintStatus, @NotNull(message = "*Start date is required.") LocalDate startDate, @NotNull(message = "*End date is required.") LocalDate endDate, Project project, List<Task> tasks) {
        this.name = name;
        this.description = description;
        this.sprintPhase = sprintPhase;
        this.effortPlanned = effortPlanned;
        this.effortBurnt = effortBurnt;
        this.sprintStatus = sprintStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.tasks = tasks;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SprintPhase getSprintPhase() {
        return sprintPhase;
    }

    public void setSprintPhase(SprintPhase sprintPhase) {
        this.sprintPhase = sprintPhase;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
