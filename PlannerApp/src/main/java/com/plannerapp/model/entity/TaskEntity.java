package com.plannerapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @Column(name = "due_date", nullable = false)
    @FutureOrPresent(message = "Due Date must be in the future!")
    private LocalDate dueDate;

    @ManyToOne
    @NotNull(message = "You must select a priority!")
    private PriorityEntity priority;

    @ManyToOne
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public TaskEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityEntity getPriority() {
        return priority;
    }

    public TaskEntity setPriority(PriorityEntity priority) {
        this.priority = priority;
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
