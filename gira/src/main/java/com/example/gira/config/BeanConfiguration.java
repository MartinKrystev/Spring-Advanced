package com.example.gira.config;

import com.example.gira.domain.beans.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @SessionScope
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }
}
