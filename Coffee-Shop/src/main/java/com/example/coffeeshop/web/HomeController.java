package com.example.coffeeshop.web;

import com.example.coffeeshop.domain.beans.LoggedUser;
import com.example.coffeeshop.domain.entities.OrderEntity;
import com.example.coffeeshop.domain.entities.UserEntity;
import com.example.coffeeshop.domain.enums.CategoryType;
import com.example.coffeeshop.services.OrderService;
import com.example.coffeeshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public HomeController(LoggedUser loggedUser, OrderService orderService, UserService userService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<OrderEntity> allOrders = this.orderService.findAll();
        int ordersSum = this.orderService.allOrdersCount();


        List<UserEntity> allUsers = this.userService.findAll();

        model.addAttribute("allOrders", allOrders);
        model.addAttribute("ordersSum", ordersSum);
        model.addAttribute("allUsers", allUsers);

        return "home";
    }

    @GetMapping("/order/ready{id}")
    public String orderReady(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.orderService.orderReady(id);
        return "redirect:/home";
    }

    //Model Attributes
    @ModelAttribute(name = "categories")
    public CategoryType[] categories() {
        return CategoryType.values();
    }
}
