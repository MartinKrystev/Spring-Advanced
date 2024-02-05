package com.example.battleships.domain.models;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BattleDto {

    @Positive
    private Long attackerId;

    @Positive
    private Long defenderId;
}
