package com.smt.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smt.example.dto.IGeneralDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User Entity @author Turuu
 */

@Entity
@Table(name = "user")
public class User implements IGeneralDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Size(min = 4, max = 255)
    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Size(min = 4, max = 255)
    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
    
    @ManyToMany(mappedBy = "members")
    private Set<Project> projects = new HashSet<Project>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<StatusChangeRecord> statusChangeRecords;

    @JsonIgnore
    public List<StatusChangeRecord> getStatusChangeRecords() {
        return statusChangeRecords;
    }

    public void setStatusChangeRecords(List<StatusChangeRecord> statusChangeRecords) {
        this.statusChangeRecords = statusChangeRecords;
    }

    @JsonIgnore
    public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updateDate) {
        this.updatedAt = updateDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	public User() {

	}

	public User(Integer id, @Size(min = 4, max = 255) String username, @Size(min = 4, max = 255) String email,
			@Size(min = 8, message = "Minimum password length: 8 characters") String password, Date createdAt,
			Date updatedAt, List<Role> roles, Set<Project> projects) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
		this.projects = projects;
	}
    
}
