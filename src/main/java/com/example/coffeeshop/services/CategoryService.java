package com.example.coffeeshop.services;

import com.example.coffeeshop.domain.entities.CategoryEntity;
import com.example.coffeeshop.domain.enums.CategoryType;

public interface CategoryService {

    void initCategories();
    CategoryEntity findByName(CategoryType categoryType);
}
