package com.example.manager.service;

import com.example.manager.model.Task;

import java.util.List;

public interface TaskManagerService {
    List<Task> prioritySortStrategy();
    List<Task> statusSortStrategy();
}
