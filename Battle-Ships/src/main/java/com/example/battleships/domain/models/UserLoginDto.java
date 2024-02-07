package com.example.battleships.domain.models;

import com.example.battleships.validations.UserLoginValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@UserLoginValidation
public class UserLoginDto {
    @Size(min = 3, max = 10)
    @NotNull
    private String username;

    @Size(min = 3)
    @NotNull
    private String password;
}
