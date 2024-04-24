package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.dtos.UserLoginDto;
import com.example.shoppinglist.domain.dtos.UserRegisterDto;
import com.example.shoppinglist.domain.entities.User;

public interface UserService {
    boolean isLogged();

    boolean register(UserRegisterDto userRegisterDto);

    boolean loginUser(UserLoginDto userLoginDto);

    User registerUser(UserRegisterDto userRegisterDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
