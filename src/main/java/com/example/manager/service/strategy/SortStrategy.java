package com.example.manager.service.strategy;

import com.example.manager.model.Task;

import java.util.List;

public interface SortStrategy {
    void sort(List<Task> tasks);
}
