package com.example.manager.service;

import com.example.manager.dto.TaskRequest;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskCreationService {
    void addNewTask(TaskRequest taskRequest);

    void addNewTaskList(List<TaskRequest> taskRequest);

}
