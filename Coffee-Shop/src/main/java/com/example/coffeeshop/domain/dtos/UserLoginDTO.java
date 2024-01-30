package com.example.coffeeshop.domain.dtos;

import com.example.coffeeshop.validations.LoginUserValidation;
import jakarta.validation.constraints.Size;

@LoginUserValidation
public class UserLoginDTO {

    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 3)
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
