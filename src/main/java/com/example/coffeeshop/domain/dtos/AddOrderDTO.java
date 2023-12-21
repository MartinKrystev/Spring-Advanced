package com.example.coffeeshop.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOrderDTO {
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    private BigDecimal price;

    @PastOrPresent
    private LocalDateTime orderTime;

    @NotNull
    private String category;

    @Size(min = 5)
    private String description;

    public String getName() {
        return name;
    }

    public AddOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public AddOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AddOrderDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
