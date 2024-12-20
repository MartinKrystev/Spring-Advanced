package com.example.gira.domain.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddTaskDTO {

    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5)
    private String description;

    @FutureOrPresent
    private LocalDate dueDate;

    @NotNull
    private String classification;

    public String getName() {
        return name;
    }

    public AddTaskDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddTaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public AddTaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getClassification() {
        return classification;
    }

    public AddTaskDTO setClassification(String classification) {
        this.classification = classification;
        return this;
    }
}
