package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.service.DbCartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private DbCartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "createNewCart", consumes = APPLICATION_JSON_VALUE)
    public void createNewCart(@RequestBody CartDto cartDto) {
        System.out.println("Created new Cart");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam long cartId) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public String addProductToCart(@RequestParam long cartId,
                                   @RequestParam long productId,
                                   @RequestParam double quantity) {
        return "New List of products with added product";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) {
        System.out.println("New List of products without product");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam long userId) {
        return new OrderDto(1L, new Date(), new ArrayList<>(), new UserMapper().mapToUserDto(new UserEntity()));
    }

}
