package com.example.gira.services.impl;

import com.example.gira.domain.beans.LoggedUser;
import com.example.gira.domain.dtos.AddTaskDTO;
import com.example.gira.domain.entities.ClassificationEntity;
import com.example.gira.domain.entities.TaskEntity;
import com.example.gira.domain.entities.UserEntity;
import com.example.gira.domain.enums.ClassificationName;
import com.example.gira.domain.enums.ProgressType;
import com.example.gira.repositories.ClassificationRepository;
import com.example.gira.repositories.TaskRepository;
import com.example.gira.repositories.UserRepository;
import com.example.gira.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final LoggedUser loggedUser;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ClassificationRepository classificationRepository;

    @Autowired
    public TaskServiceImpl(LoggedUser loggedUser,
                           TaskRepository taskRepository,
                           UserRepository userRepository,
                           ClassificationRepository classificationRepository) {
        this.loggedUser = loggedUser;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public List<TaskEntity> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public boolean createTask(AddTaskDTO addTaskDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(loggedUser.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }

        ClassificationName classificationName = ClassificationName.valueOf(addTaskDTO.getClassification());
        ClassificationEntity byClassificationName =
                this.classificationRepository.findByClassificationName(classificationName).get();


        TaskEntity task = new TaskEntity()
                .setName(addTaskDTO.getName())
                .setDescription(addTaskDTO.getDescription())
                .setDueDate(addTaskDTO.getDueDate())
                .setClassification(byClassificationName)
                .setUser(byUsername.get())
                .setProgress(ProgressType.OPEN);

        this.taskRepository.save(task);
        return true;
    }

    @Override
    public TaskEntity findByName(String name) {
        return this.taskRepository.findByName(name).get();
    }

    @Override
    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public void updateProgress(Long id) {
        Optional<TaskEntity> byId = this.taskRepository.findById(id);
        boolean forDelete = false;
        if (byId.isPresent()) {
            ProgressType currProgress = byId.get().getProgress();

            switch (currProgress) {
                case OPEN -> byId.get().setProgress(ProgressType.IN_PROGRESS);
                case IN_PROGRESS -> byId.get().setProgress(ProgressType.COMPLETED);
                case COMPLETED -> forDelete = true;
                default -> byId.get().setProgress(byId.get().getProgress());
//                case OTHER -> byId.get().setProgress(byId.get().getProgress());
            }

            if (forDelete) {
                this.taskRepository.deleteById(id);
            } else {
                this.taskRepository.save(byId.get());
            }

        }
    }
}
