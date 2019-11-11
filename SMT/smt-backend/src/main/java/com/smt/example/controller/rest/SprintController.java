package com.smt.example.controller.rest;

import com.smt.example.constant.Constants;
import com.smt.example.dto.ErrorDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Project;
import com.smt.example.entity.Sprint;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.ProjectService;
import com.smt.example.service.SprintService;
import com.smt.example.service.utilities.ResponseService;
import com.smt.example.utilities.LogUtilities;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1api/sprint")
@Api(tags = "SPRINT")
public class SprintController implements Constants {

    private SprintService sprintService;

    private ProjectService projectService;

    private JwtTokenProvider jwtTokenProvider;

    public SprintController(SprintService sprintService, ProjectService projectService, JwtTokenProvider jwtTokenProvider) {
        this.sprintService = sprintService;
        this.projectService = projectService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "{projectId}/getSprints", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO getSprints(@PathVariable("projectId") long projectId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][getSprints][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            ResponseDTO responseDTO = sprintService.getAllSprints(projectId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][getSprints][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][getSprints][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "{projectId}/search", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO searchSprints(@PathVariable("projectId") long projectId, @RequestBody SearcherDTO searcherDTO, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][search][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            ResponseDTO responseDTO = sprintService.getAllSprintsPaged(projectId, searcherDTO, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][search][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][search][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "detail/{sprintId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO getSprintById(@PathVariable("sprintId") long sprintId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][getSprintById][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            ResponseDTO responseDTO = sprintService.getSprintById(sprintId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][getSprintById][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][search][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "{projectId}/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO addSprint(@PathVariable("projectId") long projectId, @RequestBody Sprint sprint, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][add][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Project tempProject = projectService.getProjectById(projectId);

            sprint.setProject(tempProject);

            ResponseDTO responseDTO = sprintService.addSprint(sprint, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][add][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][add][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "{sprintId}/update", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO updateSprint(@PathVariable("sprintId") long sprintId, @RequestBody Sprint sprint, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][update][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Sprint currentSprint = sprintService.getSprintById(sprintId);

            sprint.setSprintId(currentSprint.getSprintId());
            sprint.setProject(currentSprint.getProject());

            ResponseDTO responseDTO = sprintService.updateSprint(sprint, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][update][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][update][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "{sprintId}/delete", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO deleteSprint(@PathVariable("sprintId") long sprintId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][delete][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            sprintService.deleteSprintById(sprintId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][delete][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][delete][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "{sprintId}/start", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO startSprint(@PathVariable("sprintId") long sprintId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][start][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            sprintService.startSprint(sprintId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][start][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][start][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "{sprintId}/close", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO closeSprint(@PathVariable("sprintId") long sprintId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][close][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            sprintService.startSprint(sprintId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][sprint][close][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][sprint][close][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }
}
