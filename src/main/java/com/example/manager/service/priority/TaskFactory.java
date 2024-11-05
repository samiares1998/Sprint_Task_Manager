package com.example.manager.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskFactory {
    private final HighPriorityTaskParent highPriorityTaskParent;
    private final LowPriorityTaskParent lowPriorityTaskParent;

    public TaskParent createTask(String priority) {
        switch (priority) {
            case "HIGH":
                return highPriorityTaskParent;
            case "LOW":
                return lowPriorityTaskParent;
            default:
                throw new IllegalArgumentException("Prioridad no soportada");
        }
    }
}
