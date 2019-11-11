package com.smt.example.serviceimpl;

import com.smt.example.dto.ListDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Task;
import com.smt.example.repository.TaskRepository;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.TaskService;
import com.smt.example.service.utilities.HelperDTOService;
import com.smt.example.service.utilities.ResponseService;
import com.smt.example.utilities.LogUtilities;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Task ServiceImpl @author Turuu
 */

@Service
public class TaskServiceImpl implements TaskService {

    private JwtTokenProvider jwtTokenProvider;

    private HelperDTOService helperDTOService;

    private TaskRepository taskRepository;

    public TaskServiceImpl(JwtTokenProvider jwtTokenProvider, HelperDTOService helperDTOService, TaskRepository taskRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.helperDTOService = helperDTOService;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseDTO getAllTasks(long projectId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.get.all][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            List<Task> tasks = taskRepository.findTasksByProjectProjectIdAndSprintNull(projectId, Sort.by(Sort.Direction.DESC, "createdDate"));
            LogUtilities.info(this.getClass().getName(), "[srvc][task.get.all][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, new ListDTO<>(tasks)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.get.all][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO getAllTasksBySprintId(long sprintId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.get.bysprintid][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            List<Task> tasks = taskRepository.findTasksBySprintSprintId(sprintId, Sort.by(Sort.Direction.DESC, "createdDate"));
            LogUtilities.info(this.getClass().getName(), "[srvc][task.get.bysprintid][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, new ListDTO<>(tasks)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.get.bysprintid][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO getAllTasksPaged(long projectId, SearcherDTO searcherDTO, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.get.paged][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            Page<Task> tasksPaged;
            if (StringUtils.isBlank(searcherDTO.getKeyWord())) {
                tasksPaged = taskRepository.findTasksByProjectProjectId(projectId, PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "taskId"));
            } else {
                tasksPaged = taskRepository.findTasksByProjectProjectIdAndNameStartsWith(projectId, searcherDTO.getKeyWord(), PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "taskId"));
            }
            LogUtilities.info(this.getClass().getName(), "[srvc][task.get.paged][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, helperDTOService.getPaginationDTO(tasksPaged)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.get.paged][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO addTask(Task task, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.add][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            taskRepository.save(task);
            LogUtilities.info(this.getClass().getName(), "[srvc][task.add][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, task).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.add][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public Task getTaskById(long taskId) {
        return taskRepository.findTaskByTaskId(taskId);
    }

    @Override
    public ResponseDTO updateTask(Task task, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.update][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            task = taskRepository.save(task);
            LogUtilities.info(this.getClass().getName(), "[srvc][task.update][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, task).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.update][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    @Transactional
    public void deleteTaskById(long taskId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][task.delete][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            taskRepository.deleteTaskByTaskId(taskId);
            LogUtilities.info(this.getClass().getName(), "[srvc][task.delete][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][task.delete][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }
}
