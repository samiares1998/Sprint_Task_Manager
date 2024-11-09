package com.example.manager.service.command;

import com.example.manager.model.Status;
import com.example.manager.model.Task;

public class ReopenTaskCommand implements Command {
    private Task task;

    public ReopenTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        if (task.getStatus() == Status.COMPLETED) {
            task.setStatus(Status.IN_PROGRESS);
            System.out.println("Tarea reabierta: " + task.getDescription());
        } else {
            System.out.println("Solo se pueden reabrir tareas completadas.");
        }
    }
}