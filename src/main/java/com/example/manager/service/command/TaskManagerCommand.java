package com.example.manager.service.command;

import com.example.manager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerCommand {
    private List<Task> tasks = new ArrayList<>();
    private List<Command> commandHistory = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Tarea añadida: " + task.getDescription());
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command); // Guardamos el comando en el historial
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
