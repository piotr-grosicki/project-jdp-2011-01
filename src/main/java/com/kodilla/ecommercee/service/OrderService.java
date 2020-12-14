package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<OrderEntity> getAllOrders() {
        return repository.findAll();
    }

    public Optional<OrderEntity> getOrder(Long id) {
        return repository.findById(id);
    }

    public OrderEntity saveOrder(OrderEntity order) {
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

}
