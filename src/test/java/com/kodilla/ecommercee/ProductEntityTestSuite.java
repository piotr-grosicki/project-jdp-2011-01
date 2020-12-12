package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupEntity;
import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTestSuite {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateAndReadProductEntityWithAllConnections() {
        //Given
        UserEntity newUser = new UserEntity("name", "password", "email");
        OrderEntity newOrder = new OrderEntity(Date.from(Instant.now()));
        GroupEntity newProductGroup = new GroupEntity("First new product group");
        ProductEntity productOne = new ProductEntity("First product",
                "new product - 1", 100, newProductGroup);
        ProductEntity productTwo = new ProductEntity("First product",
                "new product - 2", 200, newProductGroup);

        userRepository.save(newUser);
        groupRepository.save(newProductGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);
        newOrder.setUserEntity(newUser);
        newOrder.getProducts().addAll(Arrays.asList(productOne, productTwo));
        orderRepository.save(newOrder);

        //When
        String checkUserOnOrder = newOrder.getUserEntity().getName();
        int orderSize = newOrder.getProducts().size();
        long sizeOfTheProductList = productRepository.count();
        long numberOfGroups = groupRepository.count();
        //Then
        Assert.assertNotNull(newOrder);
        Assert.assertEquals(2, sizeOfTheProductList);
        Assert.assertEquals("name", checkUserOnOrder);
        Assert.assertEquals(2, orderSize);
        Assert.assertEquals(1, numberOfGroups);
        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testUpdateProductEntityWithAllConnections() {
        //Given
        UserEntity newUser = new UserEntity("name", "password", "email");
        OrderEntity newOrder = new OrderEntity(Date.from(Instant.now()));
        GroupEntity newProductGroup = new GroupEntity("First new product group");
        ProductEntity productOne = new ProductEntity("First product",
                "new product - 1", 100, newProductGroup);
        ProductEntity productTwo = new ProductEntity("First product",
                "new product - 2", 200, newProductGroup);

        userRepository.save(newUser);
        groupRepository.save(newProductGroup);
        productRepository.save(productOne);
        productRepository.save(productTwo);
        newOrder.setUserEntity(newUser);
        newOrder.getProducts().addAll(Arrays.asList(productOne, productTwo));
        orderRepository.save(newOrder);

        //When
        String oldProductName = productOne.getProductName();
        long oldProductId = productOne.getProductId();
        productOne.setProductName("Updated product");
        String newProductName = productOne.getProductName();
        long newProductOneId = productOne.getProductId();
        int sizeOfProductListFromNewOrder = newOrder.getProducts().size();

        //Than
        Assert.assertEquals(oldProductId, newProductOneId);
        Assert.assertEquals(newProductName, "Updated product");
        Assert.assertNotEquals(oldProductName, newProductName);
        assertEquals(2, sizeOfProductListFromNewOrder);

        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }


}