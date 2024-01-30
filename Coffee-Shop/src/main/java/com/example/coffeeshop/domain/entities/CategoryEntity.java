package com.example.coffeeshop.domain.entities;

import com.example.coffeeshop.domain.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private CategoryType name;

    @Column(name = "needed_time")
    @NotNull
    private int neededTime;

    public CategoryEntity() {
    }

    public CategoryEntity(CategoryType name, int neededTime) {
        this.name = name;
        this.neededTime = neededTime;
    }

    public CategoryEntity(CategoryType categoryType) {
        this.name = categoryType;
        switch (categoryType) {
            case Cake -> this.neededTime = 10;
            case Coffee -> this.neededTime = 2;
            case Drink -> this.neededTime = 1;
            case Other -> this.neededTime = 5;
            default -> this.neededTime = 0;
        }
    }

    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryType getName() {
        return name;
    }

    public CategoryEntity setName(CategoryType name) {
        this.name = name;
        return this;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public CategoryEntity setNeededTime(int neededTime) {
        this.neededTime = neededTime;
        return this;
    }
}
