package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.beans.LoggedUser;
import com.example.shoppinglist.domain.dtos.UserLoginDto;
import com.example.shoppinglist.domain.dtos.UserRegisterDto;
import com.example.shoppinglist.domain.entities.User;
import com.example.shoppinglist.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
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

    @Override
    public User registerUser(UserRegisterDto userRegisterDto) {
        User userToSave = this.modelMapper.map(userRegisterDto, User.class);

        return this.userRepository.saveAndFlush(userToSave);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).get();
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }

}
