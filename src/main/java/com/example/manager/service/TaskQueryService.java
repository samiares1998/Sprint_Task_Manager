package com.example.manager.service;

import com.example.manager.model.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskQueryService {
    List<Task> getAllPending();
    List<Task> getAllInProgress();
    List<Task> getAllInCompleted();
    Page<Task> getAllByCreation(int page, int size);
    List<Task> getAll();
}
