package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder (OrderDto orderDto) {}

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(Long orderId) {
        return new OrderDto(1L, "Order number 1", "List of items");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(OrderDto orderDto) {
        return new OrderDto(1l, "Updated Order number 1", "Updated list of items");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(Long orderId) {}
}
