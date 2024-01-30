package com.example.coffeeshop.web;

import com.example.coffeeshop.domain.beans.LoggedUser;
import com.example.coffeeshop.domain.dtos.AddOrderDTO;
import com.example.coffeeshop.domain.enums.CategoryType;
import com.example.coffeeshop.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {
    private final LoggedUser loggedUser;
    private final OrderService orderService;

    @Autowired
    public OrderController(LoggedUser loggedUser, OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    @GetMapping("/order/add")
    public String addOrder() {
        if (this.loggedUser.isLogged()) {
            return "order-add";
        }

        return "redirect:/login";
    }

    @PostMapping("/order/add")
    public String postOrder(@Valid @ModelAttribute(name = "addOrderDTO") AddOrderDTO addOrderDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.orderService.createOrder(addOrderDTO)) {
            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", bindingResult);

            return "redirect:/order/add";
        }

        return "redirect:/home";
    }


    //Model Attributes
    @ModelAttribute(name = "addOrderDTO")
    public AddOrderDTO initAddProductDTO() {
        return new AddOrderDTO();
    }

    @ModelAttribute(name = "categories")
    public CategoryType[] categories() {
        return CategoryType.values();
    }
}
