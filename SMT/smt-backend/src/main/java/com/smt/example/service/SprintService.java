package com.smt.example.service;

import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Sprint;

import javax.servlet.http.HttpServletRequest;

/**
 * SprintService interface @author Turuu
 */

public interface SprintService {
    public abstract ResponseDTO getAllSprints(long projectId, HttpServletRequest req);

    public abstract ResponseDTO getAllSprintsPaged(long projectId, SearcherDTO searcherDTO, HttpServletRequest req);

    public abstract ResponseDTO addSprint(Sprint sprint, HttpServletRequest req);

    public abstract ResponseDTO getSprintById(long sprintId, HttpServletRequest req);

    public abstract Sprint getSprintById(long sprintId);

    public abstract ResponseDTO updateSprint(Sprint sprint, HttpServletRequest req);

    public abstract void deleteSprintById(long sprintId, HttpServletRequest req);

    public abstract void startSprint(long sprintId, HttpServletRequest req);

    public abstract void closeSprint(long sprintId, HttpServletRequest req);
}
