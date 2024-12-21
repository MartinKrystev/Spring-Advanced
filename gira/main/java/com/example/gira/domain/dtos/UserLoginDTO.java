package com.example.gira.domain.dtos;

import com.example.gira.validations.LoginUserValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@LoginUserValidation
public class UserLoginDTO {

    @Email
    private String email;

    @Size(min = 3, max = 20)
    private String password;

    public String getEmail() {
        return email;
    }

    public UserLoginDTO setEmail(String email) {
        this.email = email;
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
