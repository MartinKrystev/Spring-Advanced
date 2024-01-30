package com.example.coffeeshop.repositories;

import com.example.coffeeshop.domain.entities.CategoryEntity;
import com.example.coffeeshop.domain.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryType categoryType);
}
