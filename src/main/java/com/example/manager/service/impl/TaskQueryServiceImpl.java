package com.example.manager.service.impl;

import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.repository.TaskRepository;
import com.example.manager.repository.UserRepository;
import com.example.manager.service.TaskQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;
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

    //mejoras: aplicar redis cache para mejorar el rendimiento
    @Override
    public Page<Task> getAllByCreation(int page, int size) {
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
