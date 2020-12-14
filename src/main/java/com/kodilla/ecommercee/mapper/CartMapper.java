package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartEntity;
import com.kodilla.ecommercee.domain.OrderEntity;
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
                        t.getId(),
                        t.getName(),
                        t.getDescription(),
                        t.getPrice(),
                        t.getGroup().getId(),
                        t.getCarts().stream().
                                map(e->e.getId()).
                                collect(Collectors.toList()),
                        t.getOrders().stream().
                                map(e->e.getOrderId()).
                                collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
