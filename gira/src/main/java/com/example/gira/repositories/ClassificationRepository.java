package com.example.gira.repositories;

import com.example.gira.domain.entities.ClassificationEntity;
import com.example.gira.domain.enums.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {
    Optional<ClassificationEntity> findByClassificationName(ClassificationName name);
}
