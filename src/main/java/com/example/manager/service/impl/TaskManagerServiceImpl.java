package com.example.manager.service.impl;

import com.example.manager.model.Task;
import com.example.manager.repository.TaskRepository;
import com.example.manager.service.TaskManagerService;
import com.example.manager.service.strategy.PrioritySortStrategy;
import com.example.manager.service.strategy.StatusSortStrategy;
import com.example.manager.service.strategy.TaskManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TaskManagerServiceImpl implements TaskManagerService {
    private final TaskManager taskManager;
    private final TaskRepository taskRepository;

    @Override
    public List<Task> prioritySortStrategy() {
        taskRepository.findAll().forEach(taskManager::addTask);
        taskManager.setSortStrategy(new PrioritySortStrategy());
        return taskManager.sortTasks();
    }

    @Override
    public List<Task> statusSortStrategy() {
        taskRepository.findAll().forEach(taskManager::addTask);
        taskManager.setSortStrategy(new StatusSortStrategy());
        return taskManager.sortTasks();
    }
}
