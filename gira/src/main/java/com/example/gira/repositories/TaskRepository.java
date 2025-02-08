package com.example.gira.repositories;

import com.example.gira.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByName(String name);
    Optional<TaskEntity> findById(Long id);
}
