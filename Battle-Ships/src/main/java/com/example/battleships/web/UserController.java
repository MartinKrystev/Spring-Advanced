package com.example.battleships.web;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.models.UserLoginDto;
import com.example.battleships.domain.models.UserRegisterDto;
import com.example.battleships.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister() {
        if (this.userService.isLogged()) {
            return "redirect:/home";
        }

        return "/register";
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
