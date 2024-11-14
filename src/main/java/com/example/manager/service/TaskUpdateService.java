package com.example.manager.service;

import com.example.manager.model.Status;

public interface TaskUpdateService {
    void updateTask(Long id, Status state);
}
