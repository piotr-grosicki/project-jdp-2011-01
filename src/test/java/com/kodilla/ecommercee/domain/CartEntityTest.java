package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.DbCartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTest {

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public DbCartService dbCartService;

    @Autowired
    public UserRepository userRepository;

    private CartEntity cart1;
    private CartEntity cart2;
    private ProductEntity product1;
    private ProductEntity product2;
    private ProductEntity product3;
    private UserEntity user1;
    private UserEntity user2;

    @Before
    public void setUp() {
        //Given
        cart1 = new CartEntity();
        cart2 = new CartEntity();

        user1 = new UserEntity("user1", "password1", "user1@test.com");
        user2 = new UserEntity("user2", "password2", "user2@test.com");

        product1 = new ProductEntity("product1", "dsc1", 2.0, null);
        product2 = new ProductEntity("product2", "dsc2", 5.0, null);
        product3 = new ProductEntity("product3", "dsc3", 10.0, null);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        userRepository.save(user1);
        userRepository.save(user2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        cart1.setUserEntity(user1);
        cart1.getProductEntities().add(product1);
        cart1.getProductEntities().add(product1);
        cart1.getProductEntities().add(product2);

        cart2.setUserEntity(user2);
        cart2.getProductEntities().add(product2);
        cart2.getProductEntities().add(product3);
        cart2.getProductEntities().add(product3);
        cart2.getProductEntities().add(product3);
        cart2.getProductEntities().add(product3);
    }

    @After
    public void cleanUp() {
        productRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }


    @Test
    public void testSaveReadProductOfCart() {
        //When
        int sizeListOfProductFromCart1 = cart1.getProductEntities().size();
        int sizeListOfProductFromCart2 = cart2.getProductEntities().size();
        String userNameOfCart1 = cart1.getUserEntity().getUserName();
        String userEmailOfCart2 = cart2.getUserEntity().getUserEmail();
        String listOfProductFromCart1 = cart1.getProductEntities().stream()
                .map(ProductEntity::getProductName)
                .collect(Collectors.joining(","));
        String listOfProductFromCart2 = cart2.getProductEntities().stream()
                .map(ProductEntity::getProductName)
                .collect(Collectors.joining(","));
        long countOfCart = cartRepository.count();
        long countOfProduct = productRepository.count();
        long countOfUser = userRepository.count();

        //Then
        Assert.assertEquals(3, sizeListOfProductFromCart1);
        Assert.assertEquals(5, sizeListOfProductFromCart2);
        Assert.assertEquals("user1", userNameOfCart1);
        Assert.assertEquals("user2@test.com", userEmailOfCart2);
        Assert.assertEquals("product1,product1,product2", listOfProductFromCart1);
        Assert.assertEquals("product2,product3,product3,product3,product3", listOfProductFromCart2);
        Assert.assertEquals(2, countOfCart);
        Assert.assertEquals(3, countOfProduct);
        Assert.assertEquals(2, countOfUser);
    }

    @Test
    public void testDeleteOfCart() {
        //When
        cartRepository.deleteById(cart1.getCartId());
        long countOfCart = cartRepository.count();

        productRepository.delete(product1);
        long countOfProduct = productRepository.count();

        userRepository.delete(user1);
        long countOfUser = userRepository.count();

        //Then
        Assert.assertEquals(1, countOfCart);
        Assert.assertEquals(2, countOfProduct);
        Assert.assertEquals(1, countOfUser);

    }

    @Test
    public void testUpdateOfCart() {
        //When
        ProductEntity newProduct = new ProductEntity("newProduct", "dsc", 1.0, null);
        productRepository.save(newProduct);
        cart1.getProductEntities().add(newProduct);
        long countOfProduct = productRepository.count();
        int sizeCart1 = cart1.getProductEntities().size();
        String nameOfUpdateProduct = cart1.getProductEntities().get(3).getProductName();

        CartEntity newCart = new CartEntity();
        cartRepository.save(newCart);
        newCart.setUserEntity(user1);
        newCart.getProductEntities().add(product1);
        long countOfCart = cartRepository.count();


        //Then
        Assert.assertEquals(4, countOfProduct);
        Assert.assertEquals(4, sizeCart1);
        Assert.assertEquals("newProduct", nameOfUpdateProduct);
        Assert.assertEquals(3, countOfCart);
    }


}