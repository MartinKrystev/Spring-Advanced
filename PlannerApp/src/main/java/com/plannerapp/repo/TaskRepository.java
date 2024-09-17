package com.plannerapp.repo;

import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<List<TaskEntity>> findAllByIdNot(Long id);
    Optional<List<TaskEntity>> findAllByUserIsNull();
    Optional<List<TaskEntity>> findAllById(Long id);
    Optional<List<TaskEntity>> findAllByUserId(Long id);
    Optional<TaskEntity> findById(Long id);
}
