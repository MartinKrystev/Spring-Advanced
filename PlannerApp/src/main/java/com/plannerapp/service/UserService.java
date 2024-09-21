package com.plannerapp.service;

import com.plannerapp.model.dtos.UserLoginDTO;
import com.plannerapp.model.dtos.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO userRegisterDto);
    boolean loginUser(UserLoginDTO userLoginDTO);
}
