package com.example.manager.controller;

import com.example.manager.dto.TaskRequest;
import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.service.*;
import com.example.manager.service.facade.TaskFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;

    @PostMapping
    public ResponseEntity<Void> addNewTask(@RequestBody TaskRequest taskRequest) {
        taskFacade.addNewTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> addNewTaskList(@RequestBody List<TaskRequest> tasks) {
        taskFacade.addNewTaskList(tasks);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateTaskStatus(@PathVariable Long id, @RequestParam Status state) {
        taskFacade.updateTaskStatus(id, state);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getAllPending() {
        return ResponseEntity.ok(taskFacade.getAllPending());
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<Task>> getAllInProgress() {
        return ResponseEntity.ok(taskFacade.getAllInProgress());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompleted() {
        return ResponseEntity.ok(taskFacade.getAllCompleted());
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Task>> getByPriority(@RequestParam Priority priority) {
        return ResponseEntity.ok(taskFacade.getByPriority(priority));
    }

    @GetMapping("/sorted/priority")
    public ResponseEntity<List<Task>> getAllSortedByPriority() {
        return ResponseEntity.ok(taskFacade.getAllSortedByPriority());
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<Task>> getAllSortedByStatus() {
        return ResponseEntity.ok(taskFacade.getAllSortedByStatus());
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskFacade.getAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Task>> getAllPaginated(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(taskFacade.getAllPaginated(page, size));
    }
}
