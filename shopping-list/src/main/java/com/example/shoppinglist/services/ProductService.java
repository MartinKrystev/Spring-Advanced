package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.dtos.AddProductDto;
import com.example.shoppinglist.domain.dtos.ProductDto;
import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllByCategoryAndUserId(Category category, Long userId);
    List<List<ProductDto>> getAllProductsByCategory();
    boolean createProduct(AddProductDto addProductDto);
    void buyProduct(Long productId);
    void buyAllProducts(Long userId);
}
