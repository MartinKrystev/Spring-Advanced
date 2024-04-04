package com.example.shoppinglist.domain.dtos;

import com.example.shoppinglist.domain.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private BigDecimal price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    @Override
    public String toString() {
        return String.format("Name: %s Price: %.2f lv", name, price);
    }

}
