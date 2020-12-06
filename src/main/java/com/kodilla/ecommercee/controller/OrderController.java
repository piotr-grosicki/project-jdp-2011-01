package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private OrderService service;
    private OrderMapper mapper;

    @RequestMapping(method = GET, value = "/getAll")
    public List<OrderDto> getAllOrders() {
        return mapper.mapToOrderDtoList(service.getAllOrders());
    }

    @RequestMapping(method = POST, value = "/create", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        service.saveOrder(mapper.mapToOrderEntity(orderDto));
    }

    @RequestMapping(method = GET, value = "/get")
    public OrderDto getOrder(@RequestParam final Long orderId) {
        return mapper.mapToOrderDto(service.getOrder(orderId)
                .orElseThrow(() -> new NoSuchElementException("No order with id " + orderId))
        );
    }

    @RequestMapping(method = PUT, value = "/update")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return mapper.mapToOrderDto(service.saveOrder(mapper.mapToOrderEntity(orderDto)));
    }

    @RequestMapping(method = DELETE, value = "/delete")
    public void deleteOrder(@RequestParam Long orderId) {
        try {
            service.deleteOrder(orderId);
        } catch (Exception e) {
            throw new NoSuchElementException("No order with id " + orderId);
        }

    }

}
