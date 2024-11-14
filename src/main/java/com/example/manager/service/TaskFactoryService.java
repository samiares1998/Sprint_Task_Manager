package com.example.manager.service;

import com.example.manager.model.Priority;
import com.example.manager.model.Task;

import java.util.List;

public interface TaskFactoryService {
    List<Task> getAllPriority(Priority priority);
}
