package com.plannerapp.service;

import com.plannerapp.model.dtos.AddTaskDTO;
import com.plannerapp.model.entity.PriorityEntity;
import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.enums.PriorityName;

import java.util.List;

public interface TaskService {
    List<TaskEntity> findAllByIdNot(Long id);
    List<TaskEntity> findAllById(Long id);

    boolean createTask(AddTaskDTO addTaskDTO);
    PriorityEntity findByPriorityName(PriorityName name);

    void assignTask(Long id);
    TaskEntity findById(Long id);
    List<TaskEntity> findAllByUserId(Long id);
    List<TaskEntity> findAllByUserIsNull();

    void removeTask(Long id);

    void returnTask(Long id);
}
