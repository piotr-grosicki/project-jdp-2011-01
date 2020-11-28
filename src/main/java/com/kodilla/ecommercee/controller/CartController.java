package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
//import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.DbCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @Autowired
    private DbCartService cartService;
    @Autowired
    private Cart cart;
    @RequestMapping(method = RequestMethod.POST, value = "createNewCart", consumes = APPLICATION_JSON_VALUE)
    public void createNewCart (@RequestBody CartDto cartDto) {
        cartService.createCart(cart);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart (@RequestParam long cartId) {
        return new ArrayList<ProductDto>();
    }
    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public String addProductToCart (@RequestParam long cartId,@RequestParam long productId) {
        return "New List of products with added product";
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart (@RequestParam long cartId,@RequestParam long productId) {
        System.out.println("New List of products without product");
    }
    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder (@RequestParam long userId) {
        return new OrderDto(5L,"Name of order created by CartController", "Order content");
    }
}
