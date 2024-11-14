package com.example.manager.controller;

import com.example.manager.model.User;
import com.example.manager.service.UserTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserTaskService taskService;

    @GetMapping("/all/tasks")
    public HttpEntity<Optional<User>> getAll(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.userAllTasks(id));
    }
}
