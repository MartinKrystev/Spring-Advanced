package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.domain.beans.LoggedUser;
import com.example.spotifyplaylist.domain.models.AddSongDto;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {
    private final SongService songService;
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public SongController(SongService songService, LoggedUser loggedUser, UserService userService) {
        this.songService = songService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/song/add")
    public String addSong() {
        if (this.loggedUser.isLogged()) {
            return "song-add";
        }

        return "redirect:/login";
    }

    @PostMapping("/song/add")
    public String postSong(@Valid @ModelAttribute(name = "addSongDto") AddSongDto addSongDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addSongDto", addSongDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addSongDto", bindingResult);

            return "redirect:/song/add";
        }

        this.songService.createSong(addSongDto);

        return "redirect:/home";
    }

    @GetMapping("/song/add_playlist/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {
        this.userService.addSongToPlaylist(id, loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/remove_all")
    public String clearPlaylist() {
        this.userService.clearPlaylist(loggedUser.getId());

        return "redirect:/home";
    }


    //Model Attributes
    @ModelAttribute(name = "addSongDto")
    public AddSongDto initAddSongDto() {
        return new AddSongDto();
    }
}
