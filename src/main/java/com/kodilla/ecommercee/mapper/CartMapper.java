package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.CartEntity;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public CartEntity mapToCartEntity(final CartDto cartDto) {
        return new CartEntity(
                cartDto.getCartId());
    }
    public CartDto mapToCartDto (final CartEntity cartEntity) {
        return new CartDto(
                cartEntity.getCartId(),
                cartEntity.getUserEntity().getUserId());
    }
    public List<ProductDto> mapToProductDtoList(final List<ProductEntity> productEntityList) {
        return productEntityList.stream()
                .map(e->new ProductDto(e.getProductId(),e.getProductName(), e.getProductDescription(),e.getProductPrice()))
                .collect(Collectors.toList());

    }
}
