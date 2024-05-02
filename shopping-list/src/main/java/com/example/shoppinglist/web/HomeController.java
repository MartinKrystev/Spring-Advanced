package com.example.shoppinglist.web;

import com.example.shoppinglist.domain.beans.LoggedUser;
import com.example.shoppinglist.domain.dtos.ProductDto;
import com.example.shoppinglist.services.ProductService;
import com.example.shoppinglist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final ProductService productService;

    @Autowired
    public HomeController(UserService userService, LoggedUser loggedUser, ProductService productService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<List<ProductDto>> allProductsByCategory = this.productService.getAllProductsByCategory();

        BigDecimal pricesOfAllProducts = allProductsByCategory.stream()
                .map(list ->
                        list.stream()
                                .map(ProductDto::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("allProductsByCategory", allProductsByCategory);
        model.addAttribute("currentUserId", loggedUser.getId());
        model.addAttribute("pricesOfAllProducts", pricesOfAllProducts);

        return "home";
    }




}
