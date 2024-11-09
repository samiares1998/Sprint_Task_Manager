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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
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
        if(user!=null){
            taskRepository.save(Task
                    .builder()
                    .user(user.get())
                    .title(taskRequest.getTitle())
                    .description(taskRequest.getDescription())
                    .status(taskRequest.getStatus())
                    .priority(taskRequest.getPriority() != null ? taskRequest.getPriority() : Priority.LOW)
                    .build());
        }
    }

    @Override
    public void addNewTaskList(List<TaskRequest> taskRequest) {
        taskRequest.forEach(this::addNewTask);
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

    //mejoras: aplicar redis cache para mejorar el rendimiento
    @Override
    public Page<Task> getAllByCreation( int page,int size) {
        LocalDateTime time = LocalDateTime.of(2024, 11, 8, 10, 30); // Ejemplo: 8 de noviembre de 2024 a las 10:30
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt"); // Ordenar por `createdAt` en orden descendente
        PageRequest pageable = PageRequest.of(page, size, sort);

        return taskRepository.findByCreatedAtAfter(time, pageable);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
