package com.example.coffeeshop.services;

import com.example.coffeeshop.domain.dtos.UserLoginDTO;
import com.example.coffeeshop.domain.dtos.UserRegisterDTO;
import com.example.coffeeshop.domain.entities.UserEntity;

import java.util.List;

public interface UserService {
    boolean register(UserRegisterDTO userRegisterDto);
    UserEntity findByUsername(String username);
    boolean loginUser(UserLoginDTO userLoginDTO);
    List<UserEntity> findAll();
}

