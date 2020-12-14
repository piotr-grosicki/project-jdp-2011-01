package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperStub {
    public OrderDto mapToOrderDto(final OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getOrderId(),
                orderEntity.getDateOfOrder(),
                orderEntity.getProducts(),
                orderEntity.getUserEntity());
    }
}
