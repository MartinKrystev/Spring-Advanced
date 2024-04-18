package com.example.shoppinglist.services;

import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.enums.CategoryType;

public interface CategoryService {

    void initCategories();
    Category findByName(CategoryType categoryType);
}
