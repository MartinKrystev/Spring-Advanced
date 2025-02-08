package com.example.gira.domain.entities;

import com.example.gira.domain.enums.ProgressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 20)
    private String name;

    @Column
    @Size(min = 5)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgressType progress;

    @FutureOrPresent
    private LocalDate dueDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    private ClassificationEntity classification;

    @ManyToOne
    @NotNull
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public TaskEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProgressType getProgress() {
        return progress;
    }

    public TaskEntity setProgress(ProgressType progress) {
        this.progress = progress;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationEntity getClassification() {
        return classification;
    }

    public TaskEntity setClassification(ClassificationEntity classification) {
        this.classification = classification;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TaskEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
