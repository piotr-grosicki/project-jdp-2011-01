package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private GroupEntity group;
    private String name;
    private String description;
    private double price;
    private List<CartEntity> carts;
    private List<OrderEntity> orders;
}
