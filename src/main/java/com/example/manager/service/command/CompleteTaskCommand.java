package com.example.manager.service.command;

import com.example.manager.model.Status;
import com.example.manager.model.Task;

public class CompleteTaskCommand implements Command{

    private Task task;

    public CompleteTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        task.setStatus(Status.COMPLETED);
        System.out.println("Tarea completada: " + task.getDescription());
    }
}
