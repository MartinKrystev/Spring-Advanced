package com.example.gira.services.impl;

import com.example.gira.domain.beans.LoggedUser;
import com.example.gira.domain.dtos.UserLoginDTO;
import com.example.gira.domain.dtos.UserRegisterDTO;
import com.example.gira.domain.entities.UserEntity;
import com.example.gira.repositories.UserRepository;
import com.example.gira.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity()
                .setUsername(userRegisterDTO.getUsername())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(userRegisterDTO.getPassword());

        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if (byEmail.isEmpty()) {
            return false;
        }

        UserEntity loginCandidate = this.modelMapper.map(byEmail, UserEntity.class);

        if (loginCandidate.getId() != null) {
            this.loggedUser.setId(loginCandidate.getId());
            this.loggedUser.setUsername(loginCandidate.getUsername());

            return true;
        }

        return false;
    }
}

