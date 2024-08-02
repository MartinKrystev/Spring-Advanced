package com.example.booksrestserver.models.dtos;

public class AuthorDto {
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                ", name='" + name + '\'' +
                '}';
    }
}

