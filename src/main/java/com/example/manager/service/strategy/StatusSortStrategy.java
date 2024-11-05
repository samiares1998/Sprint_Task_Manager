package com.example.manager.service.strategy;

import com.example.manager.model.Task;

import java.util.List;

public class StatusSortStrategy implements SortStrategy{
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort((t1, t2) -> t1.getStatus().compareTo(t2.getStatus()));
    }
}
