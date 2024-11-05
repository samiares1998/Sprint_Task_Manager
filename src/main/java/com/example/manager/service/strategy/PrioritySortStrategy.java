package com.example.manager.service.strategy;

import com.example.manager.model.Task;

import java.util.List;

public class PrioritySortStrategy implements SortStrategy{
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()));
    }
}
