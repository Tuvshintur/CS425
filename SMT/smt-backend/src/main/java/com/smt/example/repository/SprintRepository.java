package com.smt.example.repository;

import com.smt.example.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smt.example.entity.Sprint;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
    List<Sprint> findSprintsByProjectProjectId(long projectId, Sort sort);
    Page<Sprint> findSprintsByProjectProjectIdAndNameStartsWith(long projectId, String keyWord, Pageable pageable);
    Page<Sprint> findSprintsByProjectProjectId(long projectId, Pageable pageable);
    Sprint findSprintBySprintId(long sprintId);
    void deleteSprintBySprintId(long sprintId);
}
