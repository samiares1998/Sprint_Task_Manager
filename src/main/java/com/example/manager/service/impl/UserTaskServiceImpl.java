package com.example.manager.service.impl;

import com.example.manager.model.User;
import com.example.manager.repository.UserRepository;
import com.example.manager.service.UserTaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> userAllTasks(Long userId) {
        return userRepository.findById(userId);
    }
}
