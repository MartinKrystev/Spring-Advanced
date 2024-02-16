package com.example.battleships.services;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.entities.CategoryType;
import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.entities.User;
import com.example.battleships.domain.models.BattleDto;
import com.example.battleships.domain.models.ShipAddDto;
import com.example.battleships.repositories.CategoryRepository;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository,
                           CategoryRepository categoryRepository,
                           LoggedUser loggedUser,
                           UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean createShip(ShipAddDto shipAddDto) {
        CategoryType categoryType = CategoryType.valueOf(shipAddDto.getCategory());
        Category category = this.categoryRepository.findByName(categoryType).get();

        User userForShip = this.userRepository.findByUsername(this.loggedUser.getUsername()).get();

        Ship shipToAdd = new Ship();
        shipToAdd.setName(shipAddDto.getName());
        shipToAdd.setHealth(shipAddDto.getHealth());
        shipToAdd.setPower(shipAddDto.getPower());
        shipToAdd.setCreated(shipAddDto.getCreated());
        shipToAdd.setCategory(category);
        shipToAdd.setUser(userForShip);

        this.shipRepository.saveAndFlush(shipToAdd);
        return true;
    }

    @Override
    public List<Ship> findAllByUser(LoggedUser currUser) {
        User user = this.userRepository.findByUsername(this.loggedUser.getUsername()).get();

        List<Ship> allShips = this.shipRepository.findAllByUser(user).get();

        return allShips;
    }

    @Override
    public List<Ship> findAllByUserIdNot(Long userId) {
        Optional<List<Ship>> allOtherShips = this.shipRepository.findAllByUserIdNot(this.loggedUser.getId());

        return allOtherShips.orElse(null);
    }

    @Override
    public List<Ship> getAllSorted() {
        return this.shipRepository.findAllByOrderByNameAscHealthAscPowerAsc().orElse(null);
    }

    @Override
    public Ship findById(Long id) {
        return this.shipRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void startBattle (BattleDto battleDto) {
        Optional<Ship> attacker = this.shipRepository.findById(battleDto.getAttackerId());
        Optional<Ship> defender = this.shipRepository.findById(battleDto.getDefenderId());



        if (attacker.isEmpty() || defender.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship shipAttack = attacker.get();
        Ship shipDefend = defender.get();

        Long newDefenderHealth = shipDefend.getHealth() - shipAttack.getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(shipDefend);
        } else {
            shipDefend.setHealth(newDefenderHealth);
            this.shipRepository.saveAndFlush(shipDefend);
        }

    }


}
