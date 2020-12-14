package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbOrderServiceStub {
    @Autowired
    private OrderRepository repository;

    public OrderEntity saveOrder (final OrderEntity order) {
        return repository.save(order);
    }
}

