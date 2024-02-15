package com.example.battleships.services;

import com.example.battleships.domain.beans.LoggedUser;
import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.models.BattleDto;
import com.example.battleships.domain.models.ShipAddDto;

import java.util.List;

public interface ShipService {
    boolean createShip(ShipAddDto shipAddDto);
    List<Ship> findAllByUser(LoggedUser user);
    List<Ship> findAllByUserIdNot(Long userId);
    List<Ship> getAllSorted();
    Ship findById(Long id);
    void startBattle (BattleDto battleDto);
}
