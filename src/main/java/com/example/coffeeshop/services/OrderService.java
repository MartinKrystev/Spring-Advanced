package com.example.coffeeshop.services;

import com.example.coffeeshop.domain.dtos.AddOrderDTO;
import com.example.coffeeshop.domain.entities.OrderEntity;

import java.util.List;

public interface OrderService {
    boolean createOrder(AddOrderDTO addOrderDTO);
    OrderEntity findByName(String name);
    List<OrderEntity> findAll();
    int allOrdersCount();
    void orderReady(Long orderId);

}
