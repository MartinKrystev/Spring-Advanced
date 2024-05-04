package com.example.shoppinglist.web;

import com.example.shoppinglist.domain.beans.LoggedUser;
import com.example.shoppinglist.domain.dtos.AddProductDto;
import com.example.shoppinglist.services.ProductService;
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
public class ProductController {
    private final LoggedUser loggedUser;
    private final ProductService productService;

    @Autowired
    public ProductController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping("/product/add")
    public String addProduct() {
        if (this.loggedUser.isLogged()) {
            return "product-add";
        }

        return "redirect:/login";
    }

    @PostMapping("/product/add")
    public String postProduct(@Valid @ModelAttribute(name = "addProductDto") AddProductDto addProductDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.productService.createProduct(addProductDto)) {
            redirectAttributes.addFlashAttribute("addProductDto", addProductDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductDto", bindingResult);

            return "redirect:/product/add";
        }

        this.productService.createProduct(addProductDto);
        return "redirect:/home";
    }

    @GetMapping("/product/buy{id}")
    public String buyProduct(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.productService.buyProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/remove_all")
    public String buyAllProducts() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.productService.buyAllProducts(loggedUser.getId());
        return "redirect:/home";
    }


    //Model Attributes
    @ModelAttribute(name = "addProductDto")
    public AddProductDto initAddProductDto() {
        return new AddProductDto();
    }


}
