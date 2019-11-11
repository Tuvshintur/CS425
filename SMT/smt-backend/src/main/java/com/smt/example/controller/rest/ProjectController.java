package com.smt.example.controller.rest;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.smt.example.entity.User;
import com.smt.example.service.UserService;
import com.smt.example.service.utilities.ResponseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.smt.example.constant.Constants.ErrorType;
import com.smt.example.dto.ErrorDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.entity.Project;
import com.smt.example.exception.RestrictionException;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.ProjectService;
import com.smt.example.utilities.LogUtilities;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1api/project")
@Api(tags = "PROJECT")
public class ProjectController {
    private ProjectService service;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    @Autowired
    public ProjectController(ProjectService service, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.service = service;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO getProjects(HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][getProjects][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            ResponseDTO responseDTO = service.getAllProjects(userService.getUserByUserName(req.getRemoteUser(), req), req);
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][getProjects][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][getProjects][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO addProject(@RequestBody Project project, HttpServletRequest req) throws IOException, RestrictionException {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][addProject][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            User tempUser = userService.getUserByUserName(req.getRemoteUser(), req);

            project.getMembers().add(tempUser);

            ResponseDTO responseDTO = service.addProject(project, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][project][addProject][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][addProject][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "updateProject/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO updateProject(@PathVariable("projectId") int projectId, @RequestBody Project project, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][updateProject][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Project currentProject = service.getProjectById(projectId);

            project.setProjectId(currentProject.getProjectId());
            ResponseDTO responseDTO = service.updateProject(currentProject, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][project][updateProject][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][updateProject][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "archiveProject/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO archieveProject(@PathVariable("projectId") long projectId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][archieveProject][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            service.archiveProjectById(projectId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][project][archieveProject][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][archieveProject][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "deleteProject/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO deleteProject(@PathVariable("projectId") long projectId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][deleteProject][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            service.deleteProjectById(projectId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][project][deleteProject][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][deleteProject][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "closeProject/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO closeProject(@PathVariable("projectId") long projectId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][project][closeProject][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            service.closeProjectById(projectId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][project][closeProject][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][project][closeProject][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

}
