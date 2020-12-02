/*
package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderEntity;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderEntityTestSuite {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void CreateTest() {
        //Given
        UserEntity user1 = new UserEntity("name1", "password1", "email1");
        UserEntity user2 = new UserEntity("name2", "password2", "email2");
        UserEntity user3 = new UserEntity("name3", "password3", "email3");
        ProductEntity product1 = new ProductEntity("prodactName1", "productDescription1", 1.0, null);
        ProductEntity product2 = new ProductEntity("prodactName2", "productDescription2", 2.0, null);
        ProductEntity product3 = new ProductEntity("prodactName3", "productDescription3", 3.0, null);
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderEntity order3 = new OrderEntity();
        order1.setUserEntity(user1);
        order2.setUserEntity(user2);
        order3.setUserEntity(user3);
        order1.getProducts().add(product1);
        order1.getProducts().add(product2);
        order2.getProducts().add(product1);
        order2.getProducts().add(product3);
        order3.getProducts().add(product2);
        order3.getProducts().add(product3);
        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        long orderOneId = order1.getOrderId();
        long orderTwoId = order2.getOrderId();
        long orderThreeId = order3.getOrderId();
        //Then
        Assert.assertNotNull(order1);
        //CleanUp
        try {
            orderRepository.deleteById((int) orderOneId);
            orderRepository.deleteById((int) orderTwoId);
            orderRepository.deleteById((int) orderThreeId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
*/
