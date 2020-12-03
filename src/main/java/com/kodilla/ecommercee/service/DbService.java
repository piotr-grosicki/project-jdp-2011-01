package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private ProductRepository productRepo;

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }
}
