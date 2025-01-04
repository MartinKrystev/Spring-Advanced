package com.example.gira.services;

import com.example.gira.domain.dtos.AddTaskDTO;
import com.example.gira.domain.entities.TaskEntity;

import java.util.List;

public interface TaskService {

    List<TaskEntity> findAll();
    boolean createTask(AddTaskDTO addTaskDTO);
    TaskEntity findByName(String name);
    void deleteTask(Long id);

    void updateProgress(Long id);
}
