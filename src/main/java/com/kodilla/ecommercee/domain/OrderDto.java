package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private Date orderDate;
    private List<ProductEntity> products;
    private UserEntity userEntity;

}


