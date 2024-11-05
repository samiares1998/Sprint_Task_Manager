package com.example.manager.repository;

import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
}
