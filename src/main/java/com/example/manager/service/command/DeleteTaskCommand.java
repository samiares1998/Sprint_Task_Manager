package com.example.manager.service.command;

import com.example.manager.model.Task;


public class DeleteTaskCommand implements Command {
   // private TaskManager taskManager;
    private Task task;

    public DeleteTaskCommand(Task task) {
      //  this.taskManager = taskManager;
        this.task = task;
    }

    @Override
    public void execute() {
       // taskManager.removeTask(task);
        System.out.println("Tarea eliminada: " + task.getDescription());
    }
}
