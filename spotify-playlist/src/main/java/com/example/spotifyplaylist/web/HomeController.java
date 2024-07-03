package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.domain.beans.LoggedUser;
import com.example.spotifyplaylist.domain.enums.StyleName;
import com.example.spotifyplaylist.domain.models.SongDto;
import com.example.spotifyplaylist.domain.models.UserLoginDto;
import com.example.spotifyplaylist.domain.models.UserRegisterDto;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final SongService songService;

    @Autowired
    public HomeController(UserService userService, LoggedUser loggedUser, SongService songService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @GetMapping("/register")
    public String getRegister() {
        if (this.userService.isLogged()){
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterDto") UserRegisterDto userRegisterDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDto)) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return "redirect:/register";
        }

        this.userService.registerUser(userRegisterDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        if (this.userService.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute(name = "userLoginDto") UserLoginDto userLoginDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.loginUser(userLoginDto)) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<SongDto> pop = this.songService.getStyleSongs(StyleName.POP);
        List<SongDto> rock = this.songService.getStyleSongs(StyleName.ROCK);
        List<SongDto> jazz = this.songService.getStyleSongs(StyleName.JAZZ);
        List<SongDto> userLoggedPlaylist = this.userService.getUserLoggedPlaylist(loggedUser.getId());

        int duration = userLoggedPlaylist
                .stream()
                .mapToInt(SongDto::getDuration)
                .sum();

        int min = duration / 60;
        int sec = duration % 60;

        model.addAttribute("popSongs", pop);
        model.addAttribute("rockSongs", rock);
        model.addAttribute("jazzSongs", jazz);
        model.addAttribute("userPlaylist", userLoggedPlaylist);
        model.addAttribute("currentUserId", loggedUser.getId());
        model.addAttribute("min", min);
        model.addAttribute("sec", sec);

        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (this.loggedUser.isLogged()) {
            this.loggedUser.clearFields();

            return "redirect:/home";
        }

        return "index";
    }


    //Model Attributes
    @ModelAttribute(name = "userRegisterDto")
    public UserRegisterDto initUserRegistrationDto() {
        return new UserRegisterDto();
    }

    @ModelAttribute(name = "userLoginDto")
    public UserLoginDto initUserLoginDto() {
        return new UserLoginDto();
    }
}
