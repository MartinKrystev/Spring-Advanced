package com.example.battleships.domain.models;

import com.example.battleships.domain.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShipAddDto {

    @NotNull
    private Long id;

    @Size(min = 2, max = 10)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Long health;

    @Positive
    @NotNull
    private Long power;

    @PastOrPresent
    @NotNull
    private Date created;

    @NotNull
    private String category;
}
