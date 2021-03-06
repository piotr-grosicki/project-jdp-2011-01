
package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
public class OrderEntityTestSuite {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Before
    public void cleanUp() {
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void shouldCreateAndReadTest() {
        //Given
        UserEntity user1 = new UserEntity("userName1", "usersPassword1", "userEmail1");
        UserEntity user2 = new UserEntity("userName2", "usersPassword2", "userEmail2");
        UserEntity user3 = new UserEntity("userName3", "usersPassword3", "userEmail3");
        ProductEntity product1 = new ProductEntity("productName1", "productDescription1", 1.0, null);
        ProductEntity product2 = new ProductEntity("productName2", "productDescription2", 1.0, null);
        ProductEntity product3 = new ProductEntity("productName3", "productDescription3", 1.0, null);
        ProductEntity product4 = new ProductEntity("productName4", "productDescription4", 1.0, null);
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderEntity order3 = new OrderEntity();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        order1.setUserEntity(user1);
        order1.getProducts().add(product1);
        order1.getProducts().add(product2);
        order1.getProducts().add(product3);

        order2.setUserEntity(user2);
        order2.getProducts().add(product2);
        order2.getProducts().add(product3);
        order2.getProducts().add(product4);

        order3.setUserEntity(user3);
        order3.getProducts().add(product3);
        order3.getProducts().add(product4);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        //When
        int sizeListOfProductsFromOrder1 = order1.getProducts().size();
        int sizeListOfProductsFromOrder3 = order3.getProducts().size();
        String nameOfUser = order1.getUserEntity().getName();
        String nameOfProductsFromOrder1 = order1.getProducts().stream().
                map(e -> e.getName()).
                collect(Collectors.joining("; "));
        String nameOfProductsFromOrder3 = order3.getProducts().stream().
                map(e -> e.getName()).
                collect(Collectors.joining("; "));
        long sizeOfOrderEntity = orderRepository.count();
        long sizeOfProductEntity = productRepository.count();
        long sizeOfUserEntity = userRepository.count();
        //Then
        Assert.assertNotNull(order1);
        Assert.assertEquals(3, sizeListOfProductsFromOrder1);
        Assert.assertEquals(2, sizeListOfProductsFromOrder3);
        Assert.assertEquals("userName1", nameOfUser);
        Assert.assertEquals("productName1; productName2; productName3", nameOfProductsFromOrder1);
        Assert.assertEquals("productName3; productName4", nameOfProductsFromOrder3);
        Assert.assertEquals(3, sizeOfOrderEntity);
        Assert.assertEquals(4, sizeOfProductEntity);
        Assert.assertEquals(3, sizeOfUserEntity);
        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void shouldUpdateAndDeleteTest() {
        //Given
        UserEntity user1 = new UserEntity("userName1", "usersPassword1", "userEmail1");
        UserEntity user2 = new UserEntity("userName2", "usersPassword2", "userEmail2");
        UserEntity user3 = new UserEntity("userName3", "usersPassword3", "userEmail3");
        ProductEntity product1 = new ProductEntity("productName1", "productDescription1", 1.0, null);
        ProductEntity product2 = new ProductEntity("productName2", "productDescription2", 1.0, null);
        ProductEntity product3 = new ProductEntity("productName3", "productDescription3", 1.0, null);
        ProductEntity product4 = new ProductEntity("productName4", "productDescription4", 1.0, null);
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderEntity order3 = new OrderEntity();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        order1.setUserEntity(user1);
        order1.getProducts().add(product1);
        order1.getProducts().add(product2);
        order1.getProducts().add(product3);

        order2.setUserEntity(user2);
        order2.getProducts().add(product2);
        order2.getProducts().add(product3);
        order2.getProducts().add(product4);

        order3.setUserEntity(user3);
        order3.getProducts().add(product3);
        order3.getProducts().add(product4);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        //When
        ProductEntity newProduct = new ProductEntity("newProduct", "productDescription", 1.0, null);
        productRepository.save(newProduct);
        order1.getProducts().add(newProduct);
        orderRepository.save(order1);
        int sizeListOfProductsFromOrder1 = order1.getProducts().size();
        String nameOfUpdatedProduct = order1.getProducts().get(sizeListOfProductsFromOrder1 - 1).getName();

        order2.getProducts().remove(0);
        order2.getProducts().add(0, newProduct);
        orderRepository.save(order2);
        String nameOfUpdatedProductFromOrder2 = order2.getProducts().get(0).getName();
        int sizeListOfProductsFromOrder2 = order2.getProducts().size();

        long order3Id = order3.getOrderId();
        orderRepository.deleteById(order3Id);
        long order2Id = order2.getOrderId();
        orderRepository.deleteById(order2Id);

        long sizeOfOrderEntity = orderRepository.count();
        long sizeOfProductEntity = productRepository.count();
        long sizeOfUserEntity = userRepository.count();
        //Then
        Assert.assertEquals(4, sizeListOfProductsFromOrder1);
        Assert.assertEquals("newProduct", nameOfUpdatedProduct);
        Assert.assertEquals("newProduct", nameOfUpdatedProductFromOrder2);
        Assert.assertEquals(3, sizeListOfProductsFromOrder2);
        Assert.assertEquals(1, sizeOfOrderEntity);
        Assert.assertEquals(3, sizeOfUserEntity);
        Assert.assertEquals(5, sizeOfProductEntity);
        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void shouldDeleteOrderWhenUserDeleted() {
        //Given
        UserEntity user1 = new UserEntity("userName1", "usersPassword1", "userEmail1");
        UserEntity user2 = new UserEntity("userName2", "usersPassword2", "userEmail2");
        UserEntity user3 = new UserEntity("userName3", "usersPassword3", "userEmail3");
        ProductEntity product1 = new ProductEntity("productName1", "productDescription1", 1.0, null);
        ProductEntity product2 = new ProductEntity("productName2", "productDescription2", 1.0, null);
        ProductEntity product3 = new ProductEntity("productName3", "productDescription3", 1.0, null);
        ProductEntity product4 = new ProductEntity("productName4", "productDescription4", 1.0, null);
        OrderEntity order1 = new OrderEntity();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        order1.setUserEntity(user1);
        order1.getProducts().add(product1);
        order1.getProducts().add(product2);
        order1.getProducts().add(product3);
        orderRepository.save(order1);

        //When
        long user1Id = user1.getId();
        userRepository.deleteById(user1Id);
        long sizeOfOrderEntity = orderRepository.count();
        long sizeOfProductEntity = productRepository.count();
        long sizeOfUserEntity = userRepository.count();
        //Then
        Assert.assertEquals(0, sizeOfOrderEntity);
        Assert.assertEquals(2, sizeOfUserEntity);
        Assert.assertEquals(4, sizeOfProductEntity);
        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }
}
