package com.example.shoppinglist.validations;

import com.example.shoppinglist.domain.dtos.UserLoginDto;
import com.example.shoppinglist.domain.entities.User;
import com.example.shoppinglist.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginUserValidator implements ConstraintValidator<LoginUserValidation, UserLoginDto> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LoginUserValidator(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initialize(LoginUserValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDto userLogin, ConstraintValidatorContext context) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loginCandidate.isPresent() &&
                loginCandidate.get().getPassword().equals(userLogin.getPassword());
    }
}
