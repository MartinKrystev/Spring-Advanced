package com.example.coffeeshop.web;

import com.example.coffeeshop.domain.beans.LoggedUser;
import com.example.coffeeshop.domain.dtos.UserLoginDTO;
import com.example.coffeeshop.domain.dtos.UserRegisterDTO;
import com.example.coffeeshop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public AuthController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "/register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterDTO") UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute(name = "userLoginDTO") UserLoginDTO userLoginDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.loginUser(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String getLogout() {
        if (this.loggedUser.isLogged()) {
            this.loggedUser.clearFields();

            return "redirect:/home";
        }

        return "index";
    }

    //ModelAttributes
    @ModelAttribute(name = "userRegisterDTO")
    public UserRegisterDTO initUserRegistrationDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute(name = "userLoginDTO")
    public UserLoginDTO initUserLoginDTO() {
        return new UserLoginDTO();
    }
}
