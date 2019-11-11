package com.smt.example.service;

import javax.servlet.http.HttpServletRequest;

import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Project;
import com.smt.example.entity.User;


public interface ProjectService {
	 public abstract ResponseDTO getAllProjects(User user, HttpServletRequest req);

	 public abstract ResponseDTO getAllProjectsPaged(long userId, SearcherDTO searcherDTO, HttpServletRequest req);

	 public abstract ResponseDTO addProject(Project project, HttpServletRequest req);

	 public abstract Project getProjectById(long projectId);

	 public abstract ResponseDTO updateProject(Project project, HttpServletRequest req);

	 public abstract ResponseDTO deleteProjectById(long projectId, HttpServletRequest req);

	public abstract ResponseDTO archiveProjectById(long projectId, HttpServletRequest req);

	public abstract ResponseDTO closeProjectById(long projectId, HttpServletRequest req) throws Exception;
}
