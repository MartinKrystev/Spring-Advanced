package com.example.gira.services;

import com.example.gira.domain.entities.ClassificationEntity;
import com.example.gira.domain.enums.ClassificationName;

public interface ClassificationService {
    void initClassifications();
    ClassificationEntity findByClassificationName(ClassificationName name);
}
