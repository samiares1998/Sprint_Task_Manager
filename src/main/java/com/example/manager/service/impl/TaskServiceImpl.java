package com.example.manager.service.impl;

import com.example.manager.dto.TaskRequest;
import com.example.manager.exception.ResourceNotFoundException;
import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.model.User;
import com.example.manager.repository.TaskRepository;
import com.example.manager.repository.UserRepository;
import com.example.manager.service.TaskCreationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskCreationService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

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

}
