package com.example.battleships.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.ORDINAL)
    private CategoryType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(CategoryType categoryType) {
        this.name = categoryType;
        this.description = "This category is " + categoryType;
    }

}
