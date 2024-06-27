package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.domain.beans.LoggedUser;
import com.example.spotifyplaylist.domain.entities.Song;
import com.example.spotifyplaylist.domain.entities.User;
import com.example.spotifyplaylist.domain.models.SongDto;
import com.example.spotifyplaylist.domain.models.UserLoginDto;
import com.example.spotifyplaylist.domain.models.UserRegisterDto;
import com.example.spotifyplaylist.repositories.SongRepository;
import com.example.spotifyplaylist.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final SongRepository songRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           LoggedUser loggedUser, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
    }

    @Override
    public User registerUser(UserRegisterDto userRegisterDto) {
        User userToSave = this.modelMapper.map(userRegisterDto, User.class);

        return this.userRepository.saveAndFlush(userToSave);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).get();
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        User byEmail = findByEmail(userRegisterDto.getEmail());
        if (byEmail != null) {
            return false;
        }

        User byUsername = findByUsername(userRegisterDto.getUsername());
        if (byUsername != null) {
            return false;
        }

        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());

        this.userRepository.saveAndFlush(user);
        return true;
    }


    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {

        Optional<User> byUsername = this.userRepository.findByUsername(userLoginDto.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User loginCandidate = this.modelMapper.map(byUsername, User.class);

        if (loginCandidate.getId() != null) {
            this.loggedUser.setId(loginCandidate.getId());
            this.loggedUser.setUsername(loginCandidate.getUsername());

            return true;
        }

        return false;
    }

    @Override
    public boolean isLogged() {
        return this.loggedUser.isLogged();
    }

    @Override
    public void logout() {
        this.loggedUser.clearFields();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<SongDto> getUserLoggedPlaylist(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow()
                .getPlaylist()
                .stream()
                .map(SongDto::new)
                .collect(Collectors.toList());
    }

    public void addSongToPlaylist(Long songId, Long userId) {
        Song song = this.songRepository.findById(songId).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);

        Objects.requireNonNull(user).addSong(song);

        this.userRepository.saveAndFlush(user);
    }

    public void clearPlaylist(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        Objects.requireNonNull(user).clearPlaylist();

        this.userRepository.saveAndFlush(user);
    }
}
