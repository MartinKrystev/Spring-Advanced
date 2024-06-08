package com.example.spotifyplaylist.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String performer;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String title;

    @Column(nullable = false)
    @Positive
    private int duration;

    @Column
    private Date releaseDate;

    @ManyToOne
    @NotNull
    private Style style;
}
