package com.smt.example.service;

import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Project;
import com.smt.example.entity.Task;

import javax.servlet.http.HttpServletRequest;

/**
 * TaskService interface @author Turuu
 */

public interface TaskService {
    public abstract ResponseDTO getAllTasks(long projectId, HttpServletRequest req);

    public abstract ResponseDTO getAllTasksBySprintId(long sprintId, HttpServletRequest req);

    public abstract ResponseDTO getAllTasksPaged(long projectId, SearcherDTO searcherDTO, HttpServletRequest req);

    public abstract ResponseDTO addTask(Task task, HttpServletRequest req);

    public abstract Task getTaskById(long taskId);

    public abstract ResponseDTO updateTask(Task task, HttpServletRequest req);

    public abstract void deleteTaskById(long taskId, HttpServletRequest req);
}
