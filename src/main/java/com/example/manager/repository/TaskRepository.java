package com.example.manager.repository;

import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
    //List<Task> findByCreatedAt(LocalDateTime time, Pageable pageable);

    Page<Task> findByCreatedAtAfter(LocalDateTime time, PageRequest pageable);

    @Override
    @EntityGraph(attributePaths = {"user"})
    List<Task> findAll();
}
