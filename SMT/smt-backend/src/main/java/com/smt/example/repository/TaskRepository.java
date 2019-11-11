package com.smt.example.repository;

import com.smt.example.entity.Project;
import com.smt.example.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Task Repository @author Turuu
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findTaskByTaskId(long taskId);

    List<Task> findTasksByProjectProjectIdAndSprintNull(long projectId, Sort sort);

    List<Task> findTasksBySprintSprintId(long sprintId, Sort sort);

    Page<Task> findTasksByProjectProjectId(long projectId, Pageable pageable);

    Page<Task> findTasksByProjectProjectIdAndNameStartsWith(long projectId, String keyWord, Pageable pageable);

    void deleteTaskByTaskId(long taskId);

}
