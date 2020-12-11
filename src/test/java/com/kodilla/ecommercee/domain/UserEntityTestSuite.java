package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private CartRepository cartRepository;

    private UserEntity user1;
    private UserEntity user2;

    @After
    public void cleanUp() {
        for (UserEntity user : repository.findAll())
            repository.deleteById(user.getId());

        for (CartEntity cart : cartRepository.findAll())
            cartRepository.deleteById(cart.getId());
    }

    @Before
    public void setUp() {
        user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");
        try {
            service.saveUser(user1);
            service.saveUser(user2);
        } catch (Exception e) {
            throw new IllegalStateException("The set-up method in UserEntityTestSuite not executed properly.");
        }
    }

    @Test
    public void shouldSaveNewUsers() {
        //given
        cleanUp();

        //when
        service.saveUser(user1);
        service.saveUser(user2);

        //then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldRetrieveSavedUsers() {
        //when
        Optional<UserEntity> retrievedUser1 = service.getUser(user1.getId());
        Optional<UserEntity> retrievedUser2 = service.getUser(user2.getId());

        //then
        assertEquals(user1.getId(), retrievedUser1.map(UserEntity::getId).orElse(null));
        assertEquals(user1.getName(), retrievedUser1.map(UserEntity::getName).orElse(null));
        assertEquals(user2.getId(), retrievedUser2.map(UserEntity::getId).orElse(null));
        assertEquals(user2.getName(), retrievedUser2.map(UserEntity::getName).orElse(null));
    }

    @Test
    public void shouldRetrieveBlockedUser() {
        //when
        service.blockUser(user1.getId());
        Optional<UserEntity> retrievedUser = service.getUser(user1.getId());

        //then
        assertEquals(true, retrievedUser.map(UserEntity::getIsBlocked).orElse(false));
    }

    @Test
    public void shouldReturnBlockedUser() {
        //when
        Optional<UserEntity> returnedUser = service.blockUser(user1.getId());

        //then
        assertEquals(true, returnedUser.map(UserEntity::getIsBlocked).orElse(false));
    }

    @Test
    public void shouldRetrieveCartOwnedByUser() {
        //when
        Optional<CartEntity> retrievedCart = cartRepository.findById(user1.getCart().getId());

        //then
        assertNotNull(retrievedCart.orElse(null));
    }

    @Test
    public void shouldDeleteCartWhenDeletingOwner() {
        //given
        long cartId = user1.getCart().getId();

        //when
        repository.deleteById(user1.getId());
        Optional<CartEntity> retrievedCart = cartRepository.findById(cartId);

        //then
        assertNull(retrievedCart.orElse(null));
    }

}
