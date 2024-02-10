package com.example.battleships.repositories;

import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.entities.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryType categoryType);
}
