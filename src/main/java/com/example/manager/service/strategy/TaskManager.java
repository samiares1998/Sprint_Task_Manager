package com.example.manager.service.strategy;

import com.example.manager.model.Task;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    @Setter
    private SortStrategy sortStrategy;

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Tarea añadida: " + task.getDescription());
    }

    public List<Task> sortTasks() {
        if (sortStrategy != null) {
            sortStrategy.sort(tasks);
            return tasks;
        } else {
            System.out.println("No hay estrategia de ordenación establecida.");
            return List.of();
        }
    }
}
