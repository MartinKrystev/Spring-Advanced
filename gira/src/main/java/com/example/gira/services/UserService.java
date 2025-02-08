package com.example.gira.services;

import com.example.gira.domain.dtos.UserLoginDTO;
import com.example.gira.domain.dtos.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO userRegisterDto);
    boolean loginUser(UserLoginDTO userLoginDTO);
}
