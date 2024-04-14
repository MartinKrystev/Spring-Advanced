package com.example.shoppinglist.repositories;

import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryType categoryType);
}
