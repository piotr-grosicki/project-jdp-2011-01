package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderEntity;

public class OrderMapper {

    public OrderEntity mapToOrderEntity(final OrderDto orderDto) {
        return new OrderEntity(
                orderDto.getOrderDate(),
                orderDto.getProducts(),
                orderDto.getUserEntity()
        );
    }

    public OrderDto mapToOrderDto(final OrderEntity order) {
        return new OrderDto(
                order.getOrderDate(),
                order.getProducts(),
                order.getUserEntity()
        );
    }

}
