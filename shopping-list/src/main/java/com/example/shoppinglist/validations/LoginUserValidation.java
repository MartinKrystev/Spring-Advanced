package com.example.shoppinglist.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = LoginUserValidator.class)
public @interface LoginUserValidation {
    String message() default "Username or Password doesn't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
