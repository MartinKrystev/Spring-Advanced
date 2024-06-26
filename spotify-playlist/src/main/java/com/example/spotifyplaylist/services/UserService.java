package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.entities.User;
import com.example.spotifyplaylist.domain.models.SongDto;
import com.example.spotifyplaylist.domain.models.UserLoginDto;
import com.example.spotifyplaylist.domain.models.UserRegisterDto;

import java.util.List;

public interface UserService {
    User registerUser(UserRegisterDto userRegisterDto);
    User findByEmail(String email);
    User findByUsername(String username);
    boolean register(UserRegisterDto userRegisterDto);
    boolean loginUser(UserLoginDto userLoginDto);
    boolean isLogged();
    void logout();
    User findById(Long id);
    List<SongDto> getUserLoggedPlaylist(Long id);
    void addSongToPlaylist(Long songId, Long userId);
    void clearPlaylist(Long userId);
}
