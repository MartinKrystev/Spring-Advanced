package com.plannerapp.service.impl;

import com.plannerapp.model.beans.LoggedUser;
import com.plannerapp.model.dtos.AddTaskDTO;
import com.plannerapp.model.entity.PriorityEntity;
import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final LoggedUser loggedUser;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;

    @Autowired
    public TaskServiceImpl(LoggedUser loggedUser, TaskRepository taskRepository, UserRepository userRepository, PriorityRepository priorityRepository) {
        this.loggedUser = loggedUser;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<TaskEntity> findAllByIdNot(Long id) {
        return this.taskRepository.findAllByIdNot(loggedUser.getId()).get();
    }

    @Override
    public List<TaskEntity> findAllById(Long id) {
        Optional<List<TaskEntity>> allById = this.taskRepository.findAllById(loggedUser.getId());

        return allById.orElse(null);
        // if (allById.isPresent()) {
        //            return allById.get();
        //        }
        //        return null;
    }

    @Override
    public boolean createTask(AddTaskDTO addTaskDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(loggedUser.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }

        PriorityName priorityName = PriorityName.valueOf(addTaskDTO.getPriority());
        PriorityEntity byPriorityName = this.priorityRepository.findByPriorityName(priorityName).get();


        TaskEntity task = new TaskEntity()
                .setDescription(addTaskDTO.getDescription())
                .setDueDate(addTaskDTO.getDueDate())
                .setPriority(byPriorityName)
                .setUser(null);

        this.taskRepository.save(task);
        return true;
    }

    @Override
    public PriorityEntity findByPriorityName(PriorityName name) {
        return this.priorityRepository.findByPriorityName(name).get();
    }

    @Override
    public void assignTask(Long id) {
        TaskEntity task = this.findById(id);
        UserEntity user = userRepository.getById(loggedUser.getId());

        if (task != null && task.getUser() == null) {
            task.setUser(user);
            this.taskRepository.save(task);
        }
    }

    @Override
    public TaskEntity findById(Long id) {
        return this.taskRepository.findById(id).get();
    }

    @Override
    public List<TaskEntity> findAllByUserId(Long id) {
        return this.taskRepository.findAllByUserId(loggedUser.getId()).get();
    }

    @Override
    public List<TaskEntity> findAllByUserIsNull() {
        return this.taskRepository.findAllByUserIsNull().get();
    }

    @Override
    public void removeTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public void returnTask(Long id) {
        TaskEntity task = this.taskRepository.findById(id).get();

        task.setUser(null);
        this.taskRepository.save(task);
    }
}
