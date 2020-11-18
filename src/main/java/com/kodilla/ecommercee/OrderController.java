package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    public void createOrder (OrderDto orderDto) {}

    public OrderDto getOrder(Long orderId) {
        return new OrderDto(1L, "Order number 1", "List of items");
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        return new OrderDto(1l, "Updated Order number 1", "Updated list of items");
    }

    public void deleteOrder(Long orderId) {}
}
