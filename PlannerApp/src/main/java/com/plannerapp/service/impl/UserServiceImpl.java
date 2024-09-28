package com.plannerapp.service.impl;

import com.plannerapp.model.beans.LoggedUser;
import com.plannerapp.model.dtos.UserLoginDTO;
import com.plannerapp.model.dtos.UserRegisterDTO;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity()
                .setUsername(userRegisterDTO.getUsername())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(userRegisterDTO.getPassword())
                .setAssignedTasks(new ArrayList<>());

        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        UserEntity loginCandidate = this.modelMapper.map(byUsername, UserEntity.class);

        if (loginCandidate.getId() != null) {
            this.loggedUser.setId(loginCandidate.getId());
            this.loggedUser.setUsername(loginCandidate.getUsername());

            return true;
        }

        return false;
    }
}

