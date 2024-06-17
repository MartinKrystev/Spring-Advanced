package com.example.spotifyplaylist.domain.models;

import com.example.spotifyplaylist.validations.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class UserRegisterDto {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;

    @Size(min = 3, max = 20)
    @NotNull
    private String confirmPassword;

}
