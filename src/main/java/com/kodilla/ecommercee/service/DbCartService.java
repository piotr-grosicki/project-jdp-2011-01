package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartEntity;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbCartService {
    @Autowired
    private CartRepository repository;

    public CartEntity saveCart(final CartEntity cart) {
        return repository.save(cart);
    }

    public List<ProductEntity> getAllProducts(final long cartId) {
        return repository.findById(cartId).get().getProducts();
    }

    public Optional<CartEntity> getCart(final long cartId) {
        return repository.findById(cartId);
    }
}
