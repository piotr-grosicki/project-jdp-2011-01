package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.StubProduct;
import com.kodilla.ecommercee.domain.StubUser;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {
    @Autowired
    private CartRepository cartRepository;
    @Test
    public void testCartSave(){
        //Given
        StubProduct stubProductOne = new StubProduct("String one", "String two", 10);
        List<StubProduct> stubProduct = new ArrayList<>();
        stubProduct.add(stubProductOne);
        StubUser stubUser = new StubUser(1L,"String","String",true,"String");
        Cart cart = new Cart (1, stubUser, stubProduct);
        //When
        cartRepository.save(cart);
        //Then
        long id = cart.getCartId();
        Optional<Cart> readCart = cartRepository.findById(id);
        Assert.assertTrue(readCart.isPresent());
        //CleanUp
        //cartRepository.deleteById(id);
    }
}
