package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @RequestMapping(method = RequestMethod.POST, value = "createNewCart")
    public void createNewCart (@RequestParam long userId) {
        new CartDto (1L,1L, "List of products");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<String> getProductCart (@RequestParam long cartId, @RequestParam long productId) {
        return new ArrayList<String>();
    }
    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public String addProductToCart (@RequestParam long cartId,@RequestParam long productId,@RequestParam double quantity) {
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
