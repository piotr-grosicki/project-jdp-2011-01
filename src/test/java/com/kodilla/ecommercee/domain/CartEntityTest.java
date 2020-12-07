package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.DbCartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTest {

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public DbCartService dbCartService;


    @After
    public void cleanUp() {
        try {
            List<CartEntity> savedCart = cartRepository.findAll();
            for (CartEntity cart : savedCart) {
                cartRepository.deleteById(cart.getCartId());
            }
        } catch (Exception e) {
            throw new IllegalStateException("The clean-up method in UserEntityTestSuite not executed properly.");
        }
    }


    @Test
    public void testSaveNewCart() {
        //Given
        CartEntity cart = new CartEntity();
        //??????cart.setUserEntity(new UserEntity(1L, "user", "password", "user@test.com"));

        //When
        cartRepository.save(cart);
        long id = cart.getCartId();

        //Then
        Assert.assertEquals(1, id);
    }


    @Test
    public void testSaveManyToManyCartToProduct() {
        //Given
        CartEntity cart = new CartEntity();

        ProductEntity milk = new ProductEntity("łaciate", "najlepsze mleko", 2, new GroupEntity("nabiał"));
        ProductEntity bread = new ProductEntity("staropolski", "zawsze swiezy", 5, new GroupEntity("pieczywo"));

        cart.getProductEntities().add(milk);
        cart.getProductEntities().add(milk);
        cart.getProductEntities().add(bread);


        milk.getCarts().add(cart);
        milk.getCarts().add(cart);
        bread.getCarts().add(cart);


        //When
        dbCartService.saveCart(cart);
        long cartId = cart.getCartId();


        //Then
        Assert.assertEquals(3, cartId);

    }

    @Test
    public void testDeleteUserFromCart() {
        //Given
        UserEntity user = new UserEntity();

        //When
        cartRepository.deleteById(user.getUserId());
        long userId = user.getUserId();

        //Then
        Assert.assertEquals(0, userId);
    }

    @Test
    public void testDeleteProductInCart() {
        //Given
        CartEntity cart = new CartEntity();

        ProductEntity milk = new ProductEntity("łaciate", "najlepsze mleko", 2, new GroupEntity("nabiał"));
        ProductEntity bread = new ProductEntity("staropolski", "zawsze swiezy", 5, new GroupEntity("pieczywo"));

        cart.getProductEntities().add(milk);
        cart.getProductEntities().add(milk);
        cart.getProductEntities().add(bread);


        milk.getCarts().add(cart);
        milk.getCarts().add(cart);
        bread.getCarts().add(cart);


        //When
        cartRepository.deleteById(milk.getProductId());
        long productId = milk.getProductId();


        //Then
        Assert.assertEquals(1, productId);


    }

}