package com.example.battleships.validations;

import com.example.battleships.domain.entities.User;
import com.example.battleships.domain.models.UserLoginDto;
import com.example.battleships.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserLoginValidator implements ConstraintValidator<UserLoginValidation, UserLoginDto> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserLoginValidator(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initialize(UserLoginValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDto userLogin, ConstraintValidatorContext context) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loginCandidate.isPresent() &&
                loginCandidate.get().getPassword().equals(userLogin.getPassword());
    }
}
