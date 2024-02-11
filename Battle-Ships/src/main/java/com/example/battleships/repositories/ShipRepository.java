package com.example.battleships.repositories;

import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<List<Ship>> findAllByUser(User user);
    Optional<List<Ship>> findAllByUserIdNot(Long userId);
    Optional<List<Ship>> findAllByOrderByNameAscHealthAscPowerAsc();
    Optional<Ship> findById(Long id);
}
