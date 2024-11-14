package com.example.manager.service.facade;

import com.example.manager.dto.TaskRequest;
import com.example.manager.model.Priority;
import com.example.manager.model.Status;
import com.example.manager.model.Task;
import com.example.manager.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskFacade {
    private final TaskCreationService taskCreationService;
    private final TaskFactoryService taskFactoryService;
    private final TaskManagerService taskManagerService;
    private final TaskUpdateService taskUpdateService;
    private final TaskQueryService taskQueryService;

    public void addNewTask(TaskRequest taskRequest){
        taskCreationService.addNewTask(taskRequest);
    }

    public void addNewTaskList(List<TaskRequest> taskRequest){
        taskCreationService.addNewTaskList(taskRequest);
    }

    public List<Task> getByPriority(Priority priority){
        return taskFactoryService.getAllPriority(priority);
    }

    public List<Task> getAllSortedByPriority(){
        return taskManagerService.prioritySortStrategy();
    }

    public List<Task> getAllSortedByStatus(){
        return taskManagerService.statusSortStrategy();
    }

    public void updateTaskStatus(Long id, Status state){
        taskUpdateService.updateTask(id,state);
    }

    public List<Task> getAllPending(){
        return taskQueryService.getAllPending();
    }
    public List<Task> getAllInProgress(){
        return taskQueryService.getAllInProgress();
    }
    public List<Task> getAllCompleted(){
        return taskQueryService.getAllInCompleted();
    }
    public Page<Task> getAllPaginated(int page, int size){
        return taskQueryService.getAllByCreation(page,size);
    }
    public List<Task> getAll(){
        return taskQueryService.getAll();
    }
}
