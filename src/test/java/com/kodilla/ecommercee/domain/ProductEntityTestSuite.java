package com.kodilla.ecommercee.domain;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


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
        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();

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
        String oldProductName = productOne.getName();
        long oldProductId = productOne.getId();
        productOne.setName("Updated product");
        String newProductName = productOne.getName();
        long newProductOneId = productOne.getId();
        int sizeOfProductListFromNewOrder = newOrder.getProducts().size();

        //Then
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

    @Test
    public void testDeleteProductById() {
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
        Long sizeOfGroupEntityBeforeDelete = groupRepository.count();
        Long productToDelete = productOne.getId();
        productRepository.deleteById(productToDelete);
        ProductEntity deletedProduct = productRepository.findById(productToDelete).orElse(null);
        Long sizeOfGroupEntityAfterDelete = groupRepository.count();

        //Then
        assertNull(deletedProduct);
        Assert.assertEquals(sizeOfGroupEntityBeforeDelete, sizeOfGroupEntityAfterDelete);
        Assert.assertEquals("First new product group", newProductGroup.getName());

        //cleanUp
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }
}
