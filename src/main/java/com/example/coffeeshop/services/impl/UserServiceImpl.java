package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.domain.beans.LoggedUser;
import com.example.coffeeshop.domain.dtos.UserLoginDTO;
import com.example.coffeeshop.domain.dtos.UserRegisterDTO;
import com.example.coffeeshop.domain.entities.UserEntity;
import com.example.coffeeshop.repositories.UserRepository;
import com.example.coffeeshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity()
                .setUsername(userRegisterDTO.getUsername())
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(userRegisterDTO.getPassword());

        this.userRepository.save(user);
        return true;
    }


    @Override
    public UserEntity findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
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

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> allUsers = this.userRepository.findAll();
        allUsers.sort((a, b) -> b.getAllOrders() - a.getAllOrders());
        return allUsers;
    }
}

