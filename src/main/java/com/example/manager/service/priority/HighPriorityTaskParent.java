package com.example.manager.service.priority;

import com.example.manager.model.Priority;
import com.example.manager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HighPriorityTaskParent implements TaskParent {

    private final TaskRepository taskRepository;

    @Override
    public List<com.example.manager.model.Task> execute() {
        return taskRepository.findByPriority(Priority.HIGH);
    }
}