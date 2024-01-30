package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.domain.beans.LoggedUser;
import com.example.coffeeshop.domain.dtos.AddOrderDTO;
import com.example.coffeeshop.domain.entities.CategoryEntity;
import com.example.coffeeshop.domain.entities.OrderEntity;
import com.example.coffeeshop.domain.entities.UserEntity;
import com.example.coffeeshop.domain.enums.CategoryType;
import com.example.coffeeshop.repositories.CategoryRepository;
import com.example.coffeeshop.repositories.OrderRepository;
import com.example.coffeeshop.repositories.UserRepository;
import com.example.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final LoggedUser loggedUser;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public OrderServiceImpl(LoggedUser loggedUser, OrderRepository orderRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.loggedUser = loggedUser;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean createOrder(AddOrderDTO addOrderDTO) {
//        OrderEntity orderByName = findByName(addOrderDTO.getName());
//        if (orderByName == null) {
//            return false;
//        }

        Optional<UserEntity> userById = this.userRepository.findByUsername(loggedUser.getUsername());
        if (userById.isEmpty()) {
            return false;
        }

        CategoryType categoryType = CategoryType.valueOf(addOrderDTO.getCategory());
        CategoryEntity category = this.categoryRepository.findByName(categoryType).get();

        OrderEntity order = new OrderEntity()
                .setName(addOrderDTO.getName())
                .setPrice(addOrderDTO.getPrice())
                .setOrderTime(addOrderDTO.getOrderTime())
                .setDescription(addOrderDTO.getDescription())
                .setCategory(category)
                .setEmployee(userById.get());
        //TODO: is this the right employee?

        this.orderRepository.save(order);
        return true;
    }

    @Override
    public OrderEntity findByName(String name) {
        return this.orderRepository.findByName(name).get();
    }

    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> allOrders = this.orderRepository.findAll();
        allOrders.sort((a, b) -> b.getPrice().compareTo(a.getPrice()));

        return allOrders;
    }

    @Override
    public int allOrdersCount() {
        int allOrdersSum = 0;
        for (OrderEntity order : this.orderRepository.findAll()) {

            CategoryType currCategory = order.getCategory().getName();
            switch (currCategory) {
                case Cake -> allOrdersSum += 10;
                case Coffee -> allOrdersSum += 2;
                case Drink -> allOrdersSum += 1;
                case Other -> allOrdersSum += 5;
                default -> allOrdersSum += 0;
            }
        }

        return allOrdersSum;
    }

    @Override
    public void orderReady(Long orderId) {
        this.orderRepository.deleteById(orderId);
    }


}
