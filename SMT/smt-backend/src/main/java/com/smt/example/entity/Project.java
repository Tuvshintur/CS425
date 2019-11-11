/**
 * Project Entity @author Davaa
 */
package com.smt.example.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import com.smt.example.dto.IGeneralDTO;


@Entity
@Table(name = "projects")
public class Project implements IGeneralDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @NotEmpty(message = "Project name is required. ")
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;

    private int sprintDuration;

    @NotNull(message = "*Start date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "*Due date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "*Created date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "created_user_id", nullable = false)
    private User createdUser;

    @ManyToOne
    @JoinColumn(name = "scrum_master_id", nullable = false)
    private User scrumMaster;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Sprint> sprints;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "projects_members",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
   // private List<User> members;
    private Set<User> members = new HashSet<User>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> backLog;
    
    @Column(columnDefinition = "boolean default true")
    private Boolean status;
    
    private ProjectStatus projectStatus;

    /*
     * Getters and Setters
     * */
    public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}
	
    public Boolean getStatus() {
		return status;
	}
    
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getScrumMaster() {
        return scrumMaster;
    }

    public void setScrumMaster(User scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    @JsonIgnore
    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    @JsonIgnore
    public List<Task> getBackLog() {
        return backLog;
    }

    public void setBackLog(List<Task> backLog) {
        this.backLog = backLog;
    }

    @JsonIgnore
    public Set<User> getMembers() {
		return members != null ? this.members : (this.members = new HashSet<>());
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Constructors
	 * */

	public Project() {

    }

	public Project(Long projectId, @NotEmpty(message = "Project name is required. ") String name, String description,
			int sprintDuration, @NotNull(message = "*Start date is required.") LocalDate startDate,
			@NotNull(message = "*Due date is required.") LocalDate dueDate,
			@NotNull(message = "*Created date is required.") LocalDate createdDate, User createdUser, User scrumMaster,
			List<Sprint> sprints, Set<User> members, List<Task> backLog, Boolean status, ProjectStatus projectStatus) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.sprintDuration = sprintDuration;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.createdDate = createdDate;
		this.createdUser = createdUser;
		this.scrumMaster = scrumMaster;
		this.sprints = sprints;
		this.members = members;
		this.backLog = backLog;
		this.status = status;
		this.projectStatus = projectStatus;
	}	
}
