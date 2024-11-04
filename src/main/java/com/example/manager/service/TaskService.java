package com.example.manager.service;

import com.example.manager.dto.TaskRequest;
import com.example.manager.model.Status;
import com.example.manager.model.Task;

import java.util.List;

public interface TaskService {
    void addNewTask(TaskRequest taskRequest);

    void updateTask(Long id, Status state);

    List<Task> getAllPending();

    List<Task> getAllInProgress();

    List<Task> getAllInCompleted();
}
