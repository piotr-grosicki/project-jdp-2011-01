package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapperStub {
    public ProductDto mapToProductDto(final ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getGroup().getId(),
                productEntity.getCarts().stream()
                        .map(t->t.getId())
                        .collect(Collectors.toList()),
                productEntity.getOrders().stream()
                        .map(t->t.getOrderId())
                        .collect(Collectors.toList()));
    }
}
