package com.example.battleships.web;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.models.BattleDto;
import com.example.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    @Autowired
    public BattleController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @PostMapping("/battle")
    public String battle(@Valid @ModelAttribute(name = "battleDto") BattleDto battleDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("battleDto", battleDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.battleDto", bindingResult);

            return "redirect:/home";
        }

        this.shipService.startBattle(battleDto);
        return "redirect:/home";
    }


    //Model Attributes
    @ModelAttribute(name = "battleDto")
    public BattleDto initBattleDto() {
        return new BattleDto();
    }
}
