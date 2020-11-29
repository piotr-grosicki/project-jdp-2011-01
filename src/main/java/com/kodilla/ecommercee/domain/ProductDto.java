package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private GroupEntity group;
    private List<CartEntity> carts;
    private List<OrderEntity> orders;

    public ProductDto(Long id, String name, String description, double price, GroupEntity group) {

    }
}
