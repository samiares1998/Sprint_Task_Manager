package com.example.manager.service.impl;

import com.example.manager.dto.TaskRequest;
import com.example.manager.exception.ResourceNotFoundException;
import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.model.User;
import com.example.manager.repository.TaskRepository;
import com.example.manager.repository.UserRepository;
import com.example.manager.service.TaskService;
import com.example.manager.service.priority.TaskFactory;
import com.example.manager.service.strategy.PrioritySortStrategy;
import com.example.manager.service.strategy.StatusSortStrategy;
import com.example.manager.service.strategy.TaskManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskFactory taskFactory;
    private final TaskManager taskManager;

    @Override
    public void addNewTask(TaskRequest taskRequest) {
        Optional<User> user= userRepository.findById(taskRequest.getUserId());
        user.ifPresent(value -> taskRepository.save(Task
                .builder()
                .user(value)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .build()));
    }

    @Override
    public void updateTask(Long id, Status state) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

// Si se encuentra la tarea, actualiza el estado y guarda
        task.setStatus(state);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllPending() {
        return taskRepository.findByStatus(Status.PENDING);
    }

    @Override
    public List<Task> getAllInProgress() {
        return taskRepository.findByStatus(Status.IN_PROGRESS);
    }

    @Override
    public List<Task> getAllInCompleted() {
        return taskRepository.findByStatus(Status.COMPLETED);
    }

    @Override
    public List<Task> getAllPriority(Priority priority) {
        return taskFactory.createTask(priority.name()).execute();
    }

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
