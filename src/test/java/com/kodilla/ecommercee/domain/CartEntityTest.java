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

        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product2);

        user2.getCart().getProducts().add(product2);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);

        //When
        int sizeListOfProductFromCart1 = user1.getCart().getProducts().size();
        int sizeListOfProductFromCart2 = user2.getCart().getProducts().size();
        String userNameOfCart1 = user1.getName();
        String userEmailOfCart2 = user2.getEmail();
        String listOfProductFromCart1 = user1.getCart().getProducts().stream()
                .map(ProductEntity::getName)
                .collect(Collectors.joining(","));
        String listOfProductFromCart2 = user2.getCart().getProducts().stream()
                .map(ProductEntity::getName)
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
    }

    @Test
    public void testDeleteOfCart() {
        //Given
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

        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product2);

        user2.getCart().getProducts().add(product2);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);

        //When
        userRepository.deleteById(user1.getId());
        long countOfUserByDelete = userRepository.count();
        long countOfCart = cartRepository.count();

        productRepository.delete(product1);
        long countOfProduct = productRepository.count();

        //Then
        assertEquals(1, countOfUserByDelete);
        assertEquals(1, countOfCart);
        assertEquals(2, countOfProduct);
    }

    @Test
    public void testUpdateOfCart() {
        //Given
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

        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product1);
        user1.getCart().getProducts().add(product2);

        user2.getCart().getProducts().add(product2);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);
        user2.getCart().getProducts().add(product3);

        //When
        ProductEntity newProduct = new ProductEntity("newProduct", "dsc", 1.0, null);
        productRepository.save(newProduct);
        user1.getCart().getProducts().add(newProduct);
        long countOfProduct = productRepository.count();
        int sizeCartUser1 = user1.getCart().getProducts().size();
        String nameOfUpdateProduct = user1.getCart().getProducts().get(3).getName();

        CartEntity newCart = new CartEntity();
        cartRepository.save(newCart);
        newCart.setOwner(user1);
        newCart.getProducts().add(product1);
        long countOfCart = cartRepository.count();

        //Then
        assertEquals(4, countOfProduct);
        assertEquals(4, sizeCartUser1);
        assertEquals("newProduct", nameOfUpdateProduct);
        assertEquals(3, countOfCart);
    }
}