package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public OrderEntity mapToOrderEntity(final OrderDto orderDto) {
        return new OrderEntity(
                orderDto.getOrderId(),
                orderDto.getOrderDate(),
                orderDto.getProducts(),
                new UserMapper().mapToUserEntity(orderDto.getUserDto())
        );
    }

    public OrderDto mapToOrderDto(final OrderEntity order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getProducts(),
                new UserMapper().mapToUserDto(order.getUserEntity())
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<OrderEntity> orders) {
        return orders.stream()
                .map(order -> new OrderDto(
                                order.getOrderId(),
                                order.getOrderDate(),
                                order.getProducts(),
                                new UserMapper().mapToUserDto(order.getUserEntity())
                        )
                )
                .collect(Collectors.toList());
    }

}
