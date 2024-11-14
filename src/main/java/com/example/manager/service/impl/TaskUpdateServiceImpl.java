package com.example.manager.service.impl;

import com.example.manager.exception.ResourceNotFoundException;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.repository.TaskRepository;
import com.example.manager.service.TaskUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskUpdateServiceImpl implements TaskUpdateService {
    private final TaskRepository taskRepository;
    @Override
    public void updateTask(Long id, Status state) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

// Si se encuentra la tarea, actualiza el estado y guarda
        task.setStatus(state);
        taskRepository.save(task);
    }
}
