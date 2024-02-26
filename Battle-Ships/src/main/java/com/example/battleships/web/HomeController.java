package com.example.battleships.web;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.models.BattleDto;
import com.example.battleships.services.ShipService;
import com.example.battleships.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    @Autowired
    public HomeController(UserService userService, LoggedUser loggedUser, ShipService shipService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @GetMapping("home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<Ship> ownShips = this.shipService.findAllByUser(loggedUser);
        List<Ship> enemyShips = this.shipService.findAllByUserIdNot(loggedUser.getId());
        List<Ship> allSortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("allSortedShips", allSortedShips);

        return "/home";
    }

    //Model Attributes
    @ModelAttribute(name = "battleDto")
    public BattleDto initBattleDto() {
        return new BattleDto();
    }
}
