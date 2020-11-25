package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
