package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.service.*;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private DbCartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.POST, value = "createNewCart")
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

    public void addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws ProductNotFoundException, CartNotFoundException {
        List<ProductDto> listOfProductsInCartDto = cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
        ProductDto productToAddDto = productMapper.mapToDto(productService.findById(productId).orElseThrow(ProductNotFoundException::new));
        listOfProductsInCartDto.add(productToAddDto);
        cartService.saveCart(cartService.getCart(cartId).orElseThrow(CartNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        List<ProductDto> listOfProductsInCartDto = cartMapper.mapToProductDtoList(cartService.getAllProducts(cartId));
        ProductDto productToRemoveDto = productMapper.mapToDto(productService.findById(productId).orElseThrow(ProductNotFoundException::new));
        listOfProductsInCartDto.remove(productToRemoveDto);
        cartService.saveCart(cartService.getCart(cartId).orElseThrow(CartNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestParam long cartId) throws CartNotFoundException {
        List<ProductEntity> listOfProducts = cartService.getAllProducts(cartId);
        UserEntity userEntity = cartService.getCart(cartId).get().getOwner();
        OrderEntity order = new OrderEntity(new Date());
        order.setProducts(listOfProducts);
        order.setUserEntity(userEntity);
        orderService.saveOrder(order);
        CartEntity cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().clear();
        cartService.saveCart(cart);
    }
}
