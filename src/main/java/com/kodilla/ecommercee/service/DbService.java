package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private ProductRepository productRepo;

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<ProductEntity> findById(final Long id) {
        return productRepo.findById(id);
    }
}
