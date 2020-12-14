package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public List<ProductDto> mapToProductDtoList (final List<ProductEntity> productList) {
        return productList.stream()
                .map(t -> new ProductDto(
                        t.getProductId(),
                        t.getProductName(),
                        t.getProductDescription(),
                        t.getProductPrice()))
                .collect(Collectors.toList());
    }
}
