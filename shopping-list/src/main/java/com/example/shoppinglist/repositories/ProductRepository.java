package com.example.shoppinglist.repositories;

import com.example.shoppinglist.domain.entities.Category;
import com.example.shoppinglist.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryAndUserId(Category category, Long userId);
    Optional<Product> findByName(String name);

    @Modifying
    void deleteAllByUserId(Long id);
}
