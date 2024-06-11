package com.example.spotifyplaylist.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 20)
    private String username;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String password;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Song> playlist;

    public User() {
        this.playlist = new HashSet<>();
    }

    public void addSong(Song song){
        this.playlist.add(song);
    }

    public void clearPlaylist(){
        this.playlist = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(playlist, user.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, playlist);
    }
}
