package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperStub {
    public ProductDto mapToProductDto(final ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getProductId(),
                productEntity.getProductName(),
                productEntity.getProductDescription(),
                productEntity.getProductPrice()
        );
    }
}
