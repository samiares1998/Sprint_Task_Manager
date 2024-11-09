package com.example.manager.controller;

import com.example.manager.dto.TaskRequest;
import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Void> addNewTask(@RequestBody TaskRequest body) {
        taskService.addNewTask(body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/list")
    public ResponseEntity<Void> addNewTaskList(@RequestBody List<TaskRequest> body) {
        taskService.addNewTaskList(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/update/state")
    public ResponseEntity<Void> updateTask(@RequestParam Long id,
                                           @RequestParam Status state) {
        taskService.updateTask(id,state);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get/all/pending")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Task>> getAllPending() {
        return ResponseEntity.ok(taskService.getAllPending());
    }
    @GetMapping("/get/all/in/progress")
    public ResponseEntity<List<Task>> getAllInProgress() {
        return ResponseEntity.ok(taskService.getAllInProgress());
    }
    @GetMapping("/get/all/completed")
    public ResponseEntity<List<Task>> getAllInCompleted() {
        return ResponseEntity.ok(taskService.getAllInCompleted());
    }
    @GetMapping("/priority")
    public ResponseEntity<List<Task>> priority(@RequestParam Priority priority) {
        return ResponseEntity.ok(taskService.getAllPriority(priority));
    }

    @GetMapping("/sort/priority")
    public ResponseEntity<List<Task>> allPriority() {
        return ResponseEntity.ok(taskService.prioritySortStrategy());
    }

    @GetMapping("/sort/status")
    public ResponseEntity<List<Task>> allStatus() {
        return ResponseEntity.ok(taskService.statusSortStrategy());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

}
