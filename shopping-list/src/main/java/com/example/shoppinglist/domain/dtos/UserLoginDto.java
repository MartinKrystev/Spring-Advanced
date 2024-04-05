package com.example.shoppinglist.domain.dtos;

import com.example.shoppinglist.validations.LoginUserValidation;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@LoginUserValidation
public class UserLoginDto {
    @Size(min = 3, max = 20)
    private String username;

    @Size(min = 3, max = 20)
    private String password;
}
