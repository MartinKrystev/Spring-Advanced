package com.example.battleships.services;

import com.example.battleships.domain.models.UserLoginDto;
import com.example.battleships.domain.models.UserRegisterDto;

public interface UserService {
    boolean isLogged();
    boolean register(UserRegisterDto userRegisterDto);
    boolean loginUser(UserLoginDto userLoginDto);
}
