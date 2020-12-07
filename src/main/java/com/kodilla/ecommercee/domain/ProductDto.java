package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long groupId;
    private List<Long> cartIds;
    private List<Long> orderIds;

    public ProductDto(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.groupId = product.getGroup().getId();
        this.cartIds = product.getCarts()
                .stream()
                .map(CartEntity::getCartId)
                .collect(Collectors.toList());
        this.orderIds = product.getOrders()
                .stream()
                .map(OrderEntity::getOrderId)
                .collect(Collectors.toList());
    }
}
