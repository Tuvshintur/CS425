package com.smt.example.repository;

import java.awt.print.Pageable;
import java.util.List;

import com.smt.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smt.example.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	Project findProjectByProjectId(long projectId);
	void deleteProjectByProjectId(long projectId);
	
	List<Project> findByMembers_Id(long userId, Sort sort);

	List<Project> findProjectsByMembers(User member, Sort sort);
	
	List<Project> findProjectsByMembersAndStatus(User member, boolean status, Sort sort);
	
	//List<Project> findProjectsByMembersAndStatus(User member, boolean status, Sort sort);

	/*Page<Project> findProjectByScrumMasterId(long userId, PageRequest pageRequest);

  	Page<Project> findProjectsByScrumMasterIdAndDescriptionStartsWith(long userId, String keyWord, PageRequest pageRequest);*/

}
