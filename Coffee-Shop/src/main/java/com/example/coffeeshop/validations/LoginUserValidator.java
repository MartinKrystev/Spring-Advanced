package com.example.coffeeshop.validations;

import com.example.coffeeshop.domain.dtos.UserLoginDTO;
import com.example.coffeeshop.domain.entities.UserEntity;
import com.example.coffeeshop.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginUserValidator implements ConstraintValidator<LoginUserValidation, UserLoginDTO> {
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
    public boolean isValid(UserLoginDTO userLogin, ConstraintValidatorContext context) {
        Optional<UserEntity> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loginCandidate.isPresent() &&
                loginCandidate.get().getPassword().equals(userLogin.getPassword());
    }
}
