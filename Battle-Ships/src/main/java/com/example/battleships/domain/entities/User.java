package com.example.battleships.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 10)
    private String username;

    @Column(name = "full_name", nullable = false)
    @Size(min = 5, max = 20)
    private String fullName;

    @Column(nullable = false)
    @Size(min = 3)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;
}
