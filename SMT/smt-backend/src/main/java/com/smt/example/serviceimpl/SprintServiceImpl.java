package com.smt.example.serviceimpl;

import com.smt.example.dto.ListDTO;
import com.smt.example.dto.ResponseDTO;
import com.smt.example.dto.SearcherDTO;
import com.smt.example.entity.Sprint;
import com.smt.example.entity.SprintStatus;
import com.smt.example.entity.Task;
import com.smt.example.entity.TaskStatus;
import com.smt.example.repository.SprintRepository;
import com.smt.example.security.JwtTokenProvider;
import com.smt.example.service.SprintService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Sprint ServiceImpl @author Turuu
 */

@Service
public class SprintServiceImpl implements SprintService {

    private JwtTokenProvider jwtTokenProvider;

    private HelperDTOService helperDTOService;

    private SprintRepository sprintRepository;

    public SprintServiceImpl(JwtTokenProvider jwtTokenProvider, HelperDTOService helperDTOService, SprintRepository sprintRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.helperDTOService = helperDTOService;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public ResponseDTO getAllSprints(long projectId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.get.all][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            List<Sprint> sprints = sprintRepository.findSprintsByProjectProjectId(projectId, Sort.by(Sort.Direction.DESC, "startDate"));
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.get.all][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, new ListDTO<>(sprints)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.get.all][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO getAllSprintsPaged(long projectId, SearcherDTO searcherDTO, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.get.paged][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        try {
            Page<Sprint> sprintsPaged;
            if (StringUtils.isBlank(searcherDTO.getKeyWord())) {
                sprintsPaged = sprintRepository.findSprintsByProjectProjectId(projectId, PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "sprintId"));
            } else {
                sprintsPaged = sprintRepository.findSprintsByProjectProjectIdAndNameStartsWith(projectId, searcherDTO.getKeyWord(), PageRequest.of(searcherDTO.getPage(), searcherDTO.getSize(), searcherDTO.getDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "sprintId"));
            }
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.get.paged][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, helperDTOService.getPaginationDTO(sprintsPaged)).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.get.paged][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO addSprint(Sprint sprint, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.add][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            sprintRepository.save(sprint);
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.add][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, sprint).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.add][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public ResponseDTO getSprintById(long sprintId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.getById][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            Sprint sprint = sprintRepository.findSprintBySprintId(sprintId);
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.getById][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, sprint).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.getById][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public Sprint getSprintById(long sprintId) {
        return sprintRepository.findSprintBySprintId(sprintId);
    }

    @Override
    public ResponseDTO updateSprint(Sprint sprint, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.update][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            sprint = sprintRepository.save(sprint);
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.update][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            return new ResponseService(HttpStatus.OK.value(), null, sprint).getResponse();
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.update][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    @Transactional
    public void deleteSprintById(long sprintId, HttpServletRequest req) {
        LogUtilities.info(this.getClass().getName(), "[srvc][sprint.delete][ini][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");

        try {
            sprintRepository.deleteSprintBySprintId(sprintId);
            LogUtilities.info(this.getClass().getName(), "[srvc][sprint.delete][end][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
        } catch (Exception ex) {
            LogUtilities.error(this.getClass().getName(), "[srvc][sprint.delete][unknown][ " + ex.getMessage() + "][" + req.getRemoteUser() + "][" + jwtTokenProvider.resolveToken(req) + "]");
            throw ex;
        }
    }

    @Override
    public void startSprint(long sprintId, HttpServletRequest req) {
        Sprint sprint = sprintRepository.findSprintBySprintId(sprintId);
        int sum = 0;
        List<Task> tasks = sprint.getTasks();
        if (sprint.getSprintStatus() == SprintStatus.ToDo) {
            for (Task task : tasks) {
                if (task.getStatus() == TaskStatus.NEW || task.getStatus() == TaskStatus.DEVELOPING)
                    sum = sum + task.getDevelopmentEffortPoint() + task.getTestEffortPoint();
                if (task.getStatus() == TaskStatus.TOBETESTED || task.getStatus() == TaskStatus.TESTING)
                    sum = sum + task.getTestEffortPoint();
            }
            sprint.setEffortPlanned(sum);
            sprint.setSprintStatus(SprintStatus.InProgress);
            sprint.setStartDate(LocalDate.now());
        }
    }

    @Override
    public void closeSprint(long sprintId, HttpServletRequest req) {
        Sprint sprint = sprintRepository.findSprintBySprintId(sprintId);
        int sum = 0;
        List<Task> tasks = sprint.getTasks();
        if (sprint.getSprintStatus() == SprintStatus.InProgress) {
            for (Task task : tasks) {
                if (task.getStatus() == TaskStatus.DEPLOYED || task.getStatus() == TaskStatus.APPROVED)
                    sum = sum + task.getDevelopmentEffortPoint() + task.getTestEffortPoint();
                else
                    task.getSprint().getProject().getBackLog().add(task);

                if (task.getStatus() == TaskStatus.TOBETESTED || task.getStatus() == TaskStatus.TESTING)
                    sum = sum + task.getTestEffortPoint();
            }
            sprint.setEffortBurnt(sum);
            ;
            sprint.setSprintStatus(SprintStatus.Closed);
            sprint.setEndDate(LocalDate.now());
        }
    }
}
