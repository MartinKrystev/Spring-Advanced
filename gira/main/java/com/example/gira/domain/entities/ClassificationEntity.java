package com.example.gira.domain.entities;

import com.example.gira.domain.enums.ClassificationName;
import jakarta.persistence.*;

@Entity
@Table(name = "classifications")
public class ClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;

    @Column
    private String description;

    public ClassificationEntity() {
    }

    public ClassificationEntity(ClassificationName name) {
        this.classificationName = name;
    }

    public Long getId() {
        return id;
    }

    public ClassificationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public ClassificationEntity setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClassificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
