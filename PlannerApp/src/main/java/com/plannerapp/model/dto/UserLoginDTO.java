package com.plannerapp.model.dtos;

import com.plannerapp.vallidation.LoginUserValidation;

import javax.validation.constraints.Size;

@LoginUserValidation
public class UserLoginDTO {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols!")
    private String username;

    @Size(min = 3,max = 20, message = "Password must be between 3 and 20 symbols!")
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
