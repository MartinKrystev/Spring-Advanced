package com.example.gira.services.impl;

import com.example.gira.domain.entities.ClassificationEntity;
import com.example.gira.domain.enums.ClassificationName;
import com.example.gira.repositories.ClassificationRepository;
import com.example.gira.services.ClassificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    @PostConstruct
    public void initClassifications() {
        if (this.classificationRepository.count() == 0) {
            List<ClassificationEntity> classifications = Arrays.stream(ClassificationName.values())
                    .map(ClassificationEntity::new)
                    .toList();

            this.classificationRepository.saveAll(classifications);
        }
    }

    @Override
    public ClassificationEntity findByClassificationName(ClassificationName name) {
        return this.classificationRepository.findByClassificationName(name).get();
    }

}
