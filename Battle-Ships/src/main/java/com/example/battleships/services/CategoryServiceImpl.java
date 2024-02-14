package com.example.battleships.services;

import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.entities.CategoryType;
import com.example.battleships.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initializeCategories() {
        if (this.categoryRepository.count() == 0) {

            List<Category> allCategories = Arrays.stream(CategoryType.values())
                    .map(categoryType -> new Category(categoryType))
                    .collect(Collectors.toList());

            this.categoryRepository.saveAllAndFlush(allCategories);
        }
    }
}
