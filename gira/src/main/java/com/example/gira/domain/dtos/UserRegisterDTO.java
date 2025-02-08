package com.example.gira.domain.dtos;

import com.example.gira.validations.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class UserRegisterDTO {

    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    @Size(min = 3, max = 20)
    private String password;

    @Size(min = 3, max = 20)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
