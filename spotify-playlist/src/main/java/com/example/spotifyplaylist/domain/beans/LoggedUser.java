package com.example.spotifyplaylist.domain.beans;

import com.example.spotifyplaylist.domain.entities.User;

public class LoggedUser {

    private Long id;

    private String username;

    public LoggedUser() {
    }

    public LoggedUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearFields() {
        this.id = null;
        this.username = null;
    }

    public boolean isLogged() {
        return this.getId() != null;
    }
}
