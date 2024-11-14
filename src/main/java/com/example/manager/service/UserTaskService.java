package com.example.manager.service;

import com.example.manager.model.User;

import java.util.Optional;

public interface UserTaskService {
    Optional<User> userAllTasks(Long userId);
}
