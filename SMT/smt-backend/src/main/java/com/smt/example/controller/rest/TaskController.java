package com.smt.example.controller.rest;

import com.smt.example.constant.Constants;
import com.smt.example.dto.ErrorDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.*;
import com.smt.example.exception.ValidationException;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.ProjectService;
import com.smt.example.service.SprintService;
import com.smt.example.service.TaskService;
import com.smt.example.service.UserService;
import com.smt.example.service.utilities.ResponseService;
import com.smt.example.utilities.LogUtilities;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1api/task")
@Api(tags = "TASK")
public class TaskController implements Constants {

    private TaskService taskService;

    private ProjectService projectService;

    private UserService userService;

    private JwtTokenProvider jwtTokenProvider;

    private SprintService sprintService;

    public TaskController(TaskService taskService, ProjectService projectService,
                          UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "getTasks/project/{projectId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO getTasks(@PathVariable("projectId") long projectId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][getTasks][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            ResponseDTO responseDTO = taskService.getAllTasks(projectId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][getTasks][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][getTasks][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "getTasks/sprint/{sprintId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO getTasksBySprintId(@PathVariable("sprintId") long sprintId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][getTasksBySprintId][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            ResponseDTO responseDTO = taskService.getAllTasksBySprintId(sprintId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][getTasksBySprintId][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][getTasksBySprintId][unknown][ " + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "searchTasks/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO searchTasks(@PathVariable("projectId") long projectId, @RequestBody SearcherDTO searcherDTO, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][searchTasks][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            ResponseDTO responseDTO = taskService.getAllTasksPaged(projectId, searcherDTO, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][searchTasks][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][searchAccount][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "addTask/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO addTask(@PathVariable("projectId") long projectId, @RequestBody Task task, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][addTask][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Project tempProject = projectService.getProjectById(projectId);
            task.setProject(tempProject);
            ResponseDTO responseDTO = taskService.addTask(task, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][addTask][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][addTask][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "updateTask/{taskId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO updateTask(@PathVariable("taskId") long taskId, @RequestBody Task task, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][updateTask][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Task currentTask = taskService.getTaskById(taskId);

            task.setTaskId(currentTask.getTaskId());
            task.setProject(currentTask.getProject());

            ResponseDTO responseDTO = taskService.updateTask(task, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][updateTask][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return responseDTO;

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][updateTask][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "changeStatus/{taskId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO changeStatusTask(@PathVariable("taskId") long taskId, @RequestBody HashMap<String, String> status, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][changeStatusTask][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Task currentTask = taskService.getTaskById(taskId);

            TaskStatus newTaskStatus = TaskStatus.valueOf(status.get("status"));
            if (!currentTask.getStatus().equals(newTaskStatus)) {
                StatusChangeRecord statusChangeRecord = new StatusChangeRecord(currentTask, currentTask.getStatus(), newTaskStatus, userService.getUserByUserName(req.getRemoteUser(), req));

                currentTask.setStatus(newTaskStatus);

                currentTask.getStatusChangeRecords().add(statusChangeRecord);

                ResponseDTO responseDTO = taskService.updateTask(currentTask, req);

                LogUtilities.info(this.getClass().getName(), "[ctrl][task][changeStatusTask][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

                return responseDTO;
            } else {
                throw new ValidationException("Status is same");
            }
        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][changeStatusTask][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "/addToSprint/{sprintId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO addTaskToSprint(@PathVariable("sprintId") long sprintId, @RequestBody List<Task> tasks, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][addTaskToSprint][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            Task temp;
            for (Task task : tasks) {
                temp = taskService.getTaskById(task.getTaskId());
                temp.getSprint().setSprintId(sprintId);
                taskService.updateTask(temp, req);
            }

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][addTaskToSprint][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][addTaskToSprint][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }

    @RequestMapping(value = "deleteTask/{taskId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_CLIENT') or  hasRole('ROLE_ADMIN')")
    public ResponseDTO deleteTask(@PathVariable("taskId") long taskId, HttpServletRequest req) {
        try {
            LogUtilities.info(this.getClass().getName(), "[ctrl][task][deleteTask][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            taskService.deleteTaskById(taskId, req);

            LogUtilities.info(this.getClass().getName(), "[ctrl][task][deleteTask][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

            return new ResponseService(HttpStatus.OK.value(), null, null).getResponse();

        } catch (Exception ex) {
            LogUtilities.fatal(this.getClass().getName(), "[ctrl][task][deleteTask][unknown][ " + ex.getMessage() + "]", ex);
            return new ResponseService(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDTO(null, ex.getMessage(), ErrorType.UNKNOWN)).getError();
        }
    }
}
