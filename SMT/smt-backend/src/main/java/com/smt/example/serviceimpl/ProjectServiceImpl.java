package com.smt.example.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import com.smt.example.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smt.example.dto.IGeneralDTO;
import com.smt.example.dto.ListDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Project;
import com.smt.example.entity.ProjectStatus;
import com.smt.example.entity.Sprint;
import com.smt.example.entity.SprintStatus;
import com.smt.example.entity.Task;
import com.smt.example.entity.User;
import com.smt.example.repository.ProjectRepository;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.ProjectService;
import com.smt.example.service.utilities.HelperDTOService;
import com.smt.example.service.utilities.ResponseService;
import com.smt.example.utilities.LogUtilities;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	private ProjectRepository repo;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private HelperDTOService<Project> helperDTOService;
	   
	@Autowired
	public ProjectServiceImpl(ProjectRepository repo, JwtTokenProvider jwtTokenProvider, HelperDTOService<Project> helperDTOService)  {
		this.repo = repo;
		this.jwtTokenProvider = jwtTokenProvider;
		this.helperDTOService = helperDTOService;
	}

	@Override
	public ResponseDTO getAllProjects(User user, HttpServletRequest req) {
		LogUtilities.info(this.getClass().getName(), "[srvc][project.get.all][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
		try {
			// List<Project> projects = repo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
			 List<Project> projects = repo.findProjectsByMembersAndStatus(user, true, Sort.by(Sort.Direction.DESC, "createdDate"));
			 LogUtilities.info(this.getClass().getName(), "[srvc][project.get.all][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			 return new ResponseService(HttpStatus.OK.value(), null, new ListDTO<>(projects)).getResponse();
		} catch (Exception ex) {
			LogUtilities.error(this.getClass().getName(), "[srvc][project.get.all][unknown][ " + ex.getMessage() + "]["
					+ req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			throw ex;
		}
	}

	@Override
	public ResponseDTO getAllProjectsPaged(long userId, SearcherDTO searcherDTO, HttpServletRequest req) {
		LogUtilities.info(this.getClass().getName(), "[srvc][project.get.paged][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
		try {
            Page<Project> projectsPaged;
           /* if (StringUtils.isBlank(searcherDTO.getKeyWord())) {
            	projectsPaged = repo.findProjectByScrumMasterId(userId, PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "projectId"));
            } else {
            	projectsPaged = repo.findProjectsByScrumMasterIdAndDescriptionStartsWith(userId, searcherDTO.getKeyWord(), PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "projectId"));
            }*/
            projectsPaged = repo.findAll(PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "projectId"));
            LogUtilities.info(this.getClass().getName(), "[srvc][project.get.paged][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, helperDTOService.getPaginationDTO(projectsPaged)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][project.get.paged][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
	}

	@Override
	public ResponseDTO addProject(Project project, HttpServletRequest req) {
		 LogUtilities.info(this.getClass().getName(), "[srvc][project.add][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

		try {
			
			LogUtilities.info(this.getClass().getName(), "[PROJECT]" + project.toString());
			//Set<User> members;
			//members.add(project.getMembers());
			//project.setMembers(members);
			repo.save(project);
			LogUtilities.info(this.getClass().getName(),"[srvc][project.add][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			return new ResponseService(HttpStatus.OK.value(), null, (IGeneralDTO) project).getResponse();
		} catch (Exception ex) {
			LogUtilities.error(this.getClass().getName(), "[srvc][project.add][unknown][ " + ex.getMessage() + "]["
					+ req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			throw ex;
		}
	}

	@Override
	public Project getProjectById(long projectId) {
		return repo.findProjectByProjectId(projectId);
	}

	@Override
	public ResponseDTO updateProject(Project project, HttpServletRequest req) {
		LogUtilities.info(this.getClass().getName(), "[srvc][project.update][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            project = repo.save(project);
            LogUtilities.info(this.getClass().getName(), "[srvc][project.update][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, (IGeneralDTO) project).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][project.update][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
	}

	@Override
	@Transactional
	public ResponseDTO deleteProjectById(long projectId, HttpServletRequest req) {
		 LogUtilities.info(this.getClass().getName(), "[srvc][project.delete][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

		try {
			repo.deleteProjectByProjectId(projectId);
			LogUtilities.info(this.getClass().getName(), "[srvc][project.delete][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();
		} catch (Exception ex) {
			LogUtilities.error(this.getClass().getName(), "[srvc][project.delete][unknown][ " + ex.getMessage() + "]["
					+ req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
			throw ex;
		}		
	}

	@Override
	public ResponseDTO archiveProjectById(long projectId, HttpServletRequest req) {
		LogUtilities.info(this.getClass().getName(), "[srvc][project.archieve][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
        	Project curProject = repo.findProjectByProjectId(projectId);
        	curProject.setStatus(false);
        	Project project = repo.save(curProject);
            LogUtilities.info(this.getClass().getName(), "[srvc][project.archieve][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, (IGeneralDTO) project).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][project.archieve][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
	}

	@Override
	@Transactional
	public ResponseDTO closeProjectById(long projectId, HttpServletRequest req) throws Exception{
		LogUtilities.info(this.getClass().getName(), "[srvc][project.close][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
        	Project curProject = repo.findProjectByProjectId(projectId);
        	curProject.setStatus(false);
        	
        	boolean valid = true;
        	List<Sprint> sprints = curProject.getSprints();
        	List<String> msgs = new ArrayList<>();
        	for (Sprint sprint : sprints) {
				if(sprint.getSprintStatus()!=SprintStatus.Closed) {
					valid = false; // all sprint must be closed
					msgs.add(sprint.getName());
				}
        	}
        	if(!valid)
				throw new Exception("All sprint must be closed. Following sprints are open: " + msgs.toString());
        	
        	if(curProject.getBackLog().size()>0)
        		valid = false;
        	if(!valid)
				throw new Exception("Backlog is not empty.");
        	
        	Project project = repo.save(curProject);
            LogUtilities.info(this.getClass().getName(), "[srvc][project.close][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, (IGeneralDTO) project).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][project.close][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
	}
}
