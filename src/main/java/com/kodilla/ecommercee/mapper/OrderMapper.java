package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    public OrderEntity mapToOrderEntity(final OrderDto orderDto) {
        return new OrderEntity(
                orderDto.getOrderId(),
                orderDto.getOrderDate(),
                productMapper.mapToEntitiesList(orderDto.getProductDtos()),
                userMapper.mapToUserEntity(orderDto.getUserDto())
        );
    }

    public OrderDto mapToOrderDto(final OrderEntity order) {
        return new OrderDto(
                order.getOrderId(),
                order.getDateOfOrder(),
                productMapper.mapToDtoList(order.getProducts()),
                userMapper.mapToUserDto(order.getUserEntity())
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<OrderEntity> orders) {
        return orders.stream()
                .map(order -> new OrderDto(
                                order.getOrderId(),
                                order.getDateOfOrder(),
                                productMapper.mapToDtoList(order.getProducts()),
                                new UserMapper().mapToUserDto(order.getUserEntity())
                        )
                )
                .collect(Collectors.toList());
    }

}
