package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DbProductServiceStub {
    @Autowired
    private ProductRepository repository;

    public Optional<ProductEntity> getProduct (long productId) {
        return repository.findById(productId);
    }
}
