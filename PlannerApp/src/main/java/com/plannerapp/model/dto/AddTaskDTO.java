package com.plannerapp.model.dtos;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddTaskDTO {
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @FutureOrPresent
    @NotNull(message = "Due Date must be in the future!")
    private LocalDate dueDate;

    @NotNull(message = "You must select a priority!")
    private String priority;

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

    public String getPriority() {
        return priority;
    }

    public AddTaskDTO setPriority(String priority) {
        this.priority = priority;
        return this;
    }
}
