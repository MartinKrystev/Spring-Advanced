package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.beans.LoggedUser;
import com.example.shoppinglist.domain.dtos.AddProductDto;
import com.example.shoppinglist.domain.dtos.ProductDto;
import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.entities.Product;
import com.example.shoppinglist.domain.entities.User;
import com.example.shoppinglist.domain.enums.CategoryType;
import com.example.shoppinglist.repositories.CategoryRepository;
import com.example.shoppinglist.repositories.ProductRepository;
import com.example.shoppinglist.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              LoggedUser loggedUser,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public List<ProductDto> getProductsByCategory(CategoryType categoryType) {
        Category category = this.categoryRepository.findByName(categoryType).get();

        return this.productRepository.findAllByCategoryAndUserId(category, loggedUser.getId())
                .stream()
                .map(product -> new ProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<List<ProductDto>> getAllProductsByCategory() {
        List<List<ProductDto>> productsByCategory = new ArrayList<>();

        List<ProductDto> food = this.getProductsByCategory(CategoryType.FOOD);
        List<ProductDto> drink = this.getProductsByCategory(CategoryType.DRINK);
        List<ProductDto> household = this.getProductsByCategory(CategoryType.HOUSEHOLD);
        List<ProductDto> other = this.getProductsByCategory(CategoryType.OTHER);

        productsByCategory.add(food);
        productsByCategory.add(drink);
        productsByCategory.add(household);
        productsByCategory.add(other);

        return productsByCategory;
    }

    @Override
    public boolean createProduct(AddProductDto addProductDto) {
        Optional<Product> productByName = this.productRepository.findByName(addProductDto.getName());

        if (productByName.isPresent()) {
            return false;
        }

        Optional<User> userById = this.userRepository.findById(loggedUser.getId());
        if (userById.isEmpty()) {
            return false;
        }

        CategoryType categoryType = CategoryType.valueOf(addProductDto.getCategory());
        Category category = this.categoryRepository.findByName(categoryType).get();

        Product product = new Product();
        product.setName(addProductDto.getName());
        product.setDescription(addProductDto.getDescription());
        product.setNeededBefore(addProductDto.getNeededBefore());
        product.setPrice(addProductDto.getPrice());
        product.setCategory(category);
        product.setUser(userById.get());

        this.productRepository.saveAndFlush(product);
        return true;
    }

    @Override
    public List<Product> findAllByCategoryAndUserId(Category category, Long userId) {
        return this.productRepository.findAllByCategoryAndUserId(category, userId);
    }

    public void buyProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    @Transactional
    public void buyAllProducts(Long userId) {
        this.productRepository.deleteAllByUserId(userId);
    }
}
