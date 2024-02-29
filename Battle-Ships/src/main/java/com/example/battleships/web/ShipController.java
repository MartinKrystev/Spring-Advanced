package com.example.battleships.web;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.models.ShipAddDto;
import com.example.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    @Autowired
    public ShipController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @GetMapping("/ship")
    private String getShip() {
        if(!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        return "ship-add";
    }

    @PostMapping("/ship")
    private String postShip(@Valid @ModelAttribute(name = "shipAddDto") ShipAddDto shipAddDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.shipService.createShip(shipAddDto)) {
            redirectAttributes.addFlashAttribute("shipAddDto", shipAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipAddDto", bindingResult);

            return "redirect:/ship";
        }

        return "redirect:/home";
    }


    //Model Attributes
    @ModelAttribute(name = "shipAddDto")
    public ShipAddDto initShipAddDto() {
        return new ShipAddDto();
    }
}
