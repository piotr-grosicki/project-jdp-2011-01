package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTest {

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public UserRepository userRepository;


    @Before
    public void cleanUp() {
        productRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testSaveReadProductOfCart() {
        //Given
        CartEntity cart1 = new CartEntity();
        CartEntity cart2 = new CartEntity();

        UserEntity user1 = new UserEntity("user1", "password1", "user1@test.com");
        UserEntity user2 = new UserEntity("user2", "password2", "user2@test.com");

        ProductEntity product1 = new ProductEntity("product1", "dsc1", 2.0, null);
        ProductEntity product2 = new ProductEntity("product2", "dsc2", 5.0, null);
        ProductEntity product3 = new ProductEntity("product3", "dsc3", 10.0, null);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        userRepository.save(user1);
        userRepository.save(user2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        cart1.setOwner(user1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);

        cart2.setOwner(user2);
        cart2.getProducts().add(product2);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);

        //When
        int sizeListOfProductFromCart1 = cart1.getProducts().size();
        int sizeListOfProductFromCart2 = cart2.getProducts().size();
        String userNameOfCart1 = cart1.getOwner().getName();
        String userEmailOfCart2 = cart2.getOwner().getEmail();
        String listOfProductFromCart1 = cart1.getProducts().stream()
                .map(ProductEntity::getProductName)
                .collect(Collectors.joining(","));
        String listOfProductFromCart2 = cart2.getProducts().stream()
                .map(ProductEntity::getProductName)
                .collect(Collectors.joining(","));
        long countOfCart = cartRepository.count();
        long countOfProduct = productRepository.count();
        long countOfUser = userRepository.count();

        //Then
        assertEquals(3, sizeListOfProductFromCart1);
        assertEquals(5, sizeListOfProductFromCart2);
        assertEquals("user1", userNameOfCart1);
        assertEquals("user2@test.com", userEmailOfCart2);
        assertEquals("product1,product1,product2", listOfProductFromCart1);
        assertEquals("product2,product3,product3,product3,product3", listOfProductFromCart2);
        assertEquals(2, countOfCart);
        assertEquals(3, countOfProduct);
        assertEquals(2, countOfUser);

        //Clean up
        productRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testDeleteOfCart() {
        //Given
        CartEntity cart1 = new CartEntity();
        CartEntity cart2 = new CartEntity();

        UserEntity user1 = new UserEntity("user1", "password1", "user1@test.com");
        UserEntity user2 = new UserEntity("user2", "password2", "user2@test.com");

        ProductEntity product1 = new ProductEntity("product1", "dsc1", 2.0, null);
        ProductEntity product2 = new ProductEntity("product2", "dsc2", 5.0, null);
        ProductEntity product3 = new ProductEntity("product3", "dsc3", 10.0, null);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        userRepository.save(user1);
        userRepository.save(user2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        cart1.setOwner(user1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);

        cart2.setOwner(user2);
        cart2.getProducts().add(product2);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);

        //When
        cartRepository.deleteById(cart1.getId());
        long countOfCart = cartRepository.count();

        productRepository.delete(product1);
        long countOfProduct = productRepository.count();

        userRepository.delete(user1);
        long countOfUser = userRepository.count();

        //Then
        assertEquals(1, countOfCart);
        assertEquals(2, countOfProduct);
        assertEquals(1, countOfUser);

        //Clean up
        productRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();

    }

    @Test
    public void testUpdateOfCart() {
        //Given
        CartEntity cart1 = new CartEntity();
        CartEntity cart2 = new CartEntity();

        UserEntity user1 = new UserEntity("user1", "password1", "user1@test.com");
        UserEntity user2 = new UserEntity("user2", "password2", "user2@test.com");

        ProductEntity product1 = new ProductEntity("product1", "dsc1", 2.0, null);
        ProductEntity product2 = new ProductEntity("product2", "dsc2", 5.0, null);
        ProductEntity product3 = new ProductEntity("product3", "dsc3", 10.0, null);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        userRepository.save(user1);
        userRepository.save(user2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        cart1.setOwner(user1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);

        cart2.setOwner(user2);
        cart2.getProducts().add(product2);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);

        //When
        ProductEntity newProduct = new ProductEntity("newProduct", "dsc", 1.0, null);
        productRepository.save(newProduct);
        cart1.getProducts().add(newProduct);
        long countOfProduct = productRepository.count();
        int sizeCart1 = cart1.getProducts().size();
        String nameOfUpdateProduct = cart1.getProducts().get(3).getProductName();

        CartEntity newCart = new CartEntity();
        cartRepository.save(newCart);
        newCart.setOwner(user1);
        newCart.getProducts().add(product1);
        long countOfCart = cartRepository.count();


        //Then
        assertEquals(4, countOfProduct);
        assertEquals(4, sizeCart1);
        assertEquals("newProduct", nameOfUpdateProduct);
        assertEquals(3, countOfCart);

        //Clean up
        productRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }


}