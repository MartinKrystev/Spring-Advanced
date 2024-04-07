package com.example.shoppinglist.domain.dtos;

import com.example.shoppinglist.validations.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
