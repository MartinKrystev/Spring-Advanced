package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.domain.entities.CategoryEntity;
import com.example.coffeeshop.domain.enums.CategoryType;
import com.example.coffeeshop.repositories.CategoryRepository;
import com.example.coffeeshop.services.CategoryService;
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
            List<CategoryEntity> categories = Arrays.stream(CategoryType.values())
                    .map(CategoryEntity::new)
                    .toList();

            this.categoryRepository.saveAll(categories);
        }
    }

    @Override
    public CategoryEntity findByName(CategoryType categoryType) {
        return this.categoryRepository.findByName(categoryType).get();
    }

}
