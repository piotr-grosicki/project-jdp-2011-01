package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
//import com.kodilla.ecommercee.mapper.OrderMapperStub;
import com.kodilla.ecommercee.mapper.ProductMapperStub;
import com.kodilla.ecommercee.mapper.UserMapper;

import com.kodilla.ecommercee.domain.UserEntity;

import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.domain.ProductDto;

import com.kodilla.ecommercee.service.DbCartService;
import com.kodilla.ecommercee.service.DbOrderServiceStub;
import com.kodilla.ecommercee.service.DbProductServiceStub;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private DbCartService cartService;
    @Autowired
    private DbProductServiceStub productServiceStub;
    @Autowired
    private DbOrderServiceStub productOrderStub;
    @Autowired
    private UserService userService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapperStub productMapperStub;
//    @Autowired
//    private OrderMapperStub orderMapperStub;
    @Autowired
    private UserMapper userMapper;


    @RequestMapping(method = RequestMethod.POST, value = "createNewCart"/*,consumes = APPLICATION_JSON_VALUE*/)
    //cart tworzy się wraz z userem bezsensowność metody tworzenia wózka bez przypisanego cart
    public void createNewCart(@RequestParam long userId) {
        Optional<UserEntity> user = userService.getUser(userId);
        CartEntity createdCart = new CartEntity();
        cartService.saveCart(createdCart);
        user.get().setCart(createdCart);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam long cartId) {
        return cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")

    public void addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws ProductNotFoundException {
        List<ProductDto> listOfProductsInCartDto = cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
        ProductDto productToAddDto = productMapperStub.mapToProductDto(productServiceStub.getProduct(productId).orElseThrow(ProductNotFoundException::new));
        listOfProductsInCartDto.add(productToAddDto);
        cartService.saveCart(cartService.getCart(cartId).orElseThrow(ProductNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws ProductNotFoundException {
        List<ProductDto> listOfProductsInCartDto = cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
        ProductDto productToRemoveDto = productMapperStub.mapToProductDto(productServiceStub.getProduct(productId).orElseThrow(ProductNotFoundException::new));
        listOfProductsInCartDto.remove(productToRemoveDto);
        cartService.saveCart(cartService.getCart(cartId).orElseThrow(ProductNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.POST, value = "createOrder")

    public void createOrder(@RequestParam long cartId) {
        List<ProductDto> listOfProducts = cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
        UserEntity userEntity = cartService.getCart(cartId).get().getOwner();
        UserDto userDto = userMapper.mapToUserDto(userEntity);
        OrderEntity order = new OrderEntity(new Date());

    }

}
