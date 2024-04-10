package com.example.shoppinglist.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 20)
    private String name;

    @Column(columnDefinition = "TEXT")
    @Size(min = 5)
    private String description;

    @Column(nullable = false)
    @Positive
    private BigDecimal price;

    @Column(name = "needed_before")
    @FutureOrPresent
    private LocalDateTime neededBefore;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
