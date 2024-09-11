package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "priorities")
public class PriorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "priority_name")
    @Enumerated(EnumType.STRING)
    private PriorityName priorityName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<TaskEntity> tasks;

    public PriorityEntity() {
    }

    public PriorityEntity(PriorityName priorityName) {
        this.priorityName = priorityName;
    }

    public Long getId() {
        return id;
    }

    public PriorityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public PriorityEntity setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PriorityEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public PriorityEntity setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
        return this;
    }
}
