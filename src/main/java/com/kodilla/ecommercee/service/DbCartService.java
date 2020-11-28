package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartEntity;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbCartService {
    @Autowired
    private CartRepository repository;
    public CartEntity createCart (final CartEntity cart) {
        return repository.save(cart);
    }

}
