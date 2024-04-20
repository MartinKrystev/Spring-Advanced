package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.enums.CategoryType;
import com.example.shoppinglist.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    @PostConstruct
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryType.values())
                    .map(Category::new)
                    .toList();

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }

    @Override
    public Category findByName(CategoryType categoryType) {
        return this.categoryRepository.findByName(categoryType).get();
    }


}

