package com.example.battleships.services;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.entities.User;
import com.example.battleships.domain.models.UserLoginDto;
import com.example.battleships.domain.models.UserRegisterDto;
import com.example.battleships.repositories.UserRepository;
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
    public boolean isLogged() {
        return this.loggedUser.isLogged();
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDto.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setUsername(userRegisterDto.getUsername());
        user.setFullName(userRegisterDto.getFullName());
        user.setPassword(userRegisterDto.getPassword());

        this.userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {

        Optional<User> byUsername = this.userRepository.findByUsername(userLoginDto.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User loginCandidate = this.modelMapper.map(byUsername, User.class);

        if (loginCandidate.getId() != null) {
            this.loggedUser.setId(loginCandidate.getId());
            this.loggedUser.setUsername(loginCandidate.getUsername());

            return true;
        }

        return false;
    }


}

