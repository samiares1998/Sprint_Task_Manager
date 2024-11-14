package com.example.manager.service.impl;

import com.example.manager.model.Priority;
import com.example.manager.model.Task;
import com.example.manager.service.TaskFactoryService;
import com.example.manager.service.priority.TaskFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TaskFactoryServiceImpl implements TaskFactoryService {
    private final TaskFactory taskFactory;
    @Override
    public List<Task> getAllPriority(Priority priority) {
        return taskFactory.createTask(priority.name()).execute();
    }
}
