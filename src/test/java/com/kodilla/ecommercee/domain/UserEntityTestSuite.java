package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserService;
import org.junit.After;
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

    @After
    public void cleanUp() {
        repository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void shouldSaveNewUsers() {
        //given
        UserEntity user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        UserEntity user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");

        //when
        service.saveUser(user1);
        service.saveUser(user2);

        //then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldRetrieveSavedUsers() {
        //given
        UserEntity user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        UserEntity user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");
        service.saveUser(user1);
        service.saveUser(user2);

        //when
        Optional<UserEntity> retrievedUser1 = service.getUser(user1.getId());
        Optional<UserEntity> retrievedUser2 = service.getUser(user2.getId());

        //then
        assertEquals(user1.getId(), retrievedUser1.map(UserEntity::getId)
                .orElseThrow(() -> new IllegalStateException("Object retrievedUser1 is empty.")));
        assertEquals(user1.getName(), retrievedUser1.map(UserEntity::getName)
                .orElseThrow(() -> new IllegalStateException("Object retrievedUser1 is empty.")));
        assertEquals(user2.getId(), retrievedUser2.map(UserEntity::getId)
                .orElseThrow(() -> new IllegalStateException("Object retrievedUser2 is empty.")));
        assertEquals(user2.getName(), retrievedUser2.map(UserEntity::getName)
                .orElseThrow(() -> new IllegalStateException("Object retrievedUser2 is empty.")));
    }

    @Test
    public void shouldRetrieveBlockedUser() {
        //given
        UserEntity user = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        service.saveUser(user);

        //when
        service.blockUser(user.getId());
        Optional<UserEntity> retrievedUser = service.getUser(user.getId());

        //then
        assertTrue(retrievedUser.map(UserEntity::getIsBlocked)
                .orElseThrow(() -> new IllegalStateException("Object retrievedUser is empty.")));
    }

    @Test
    public void shouldReturnBlockedUser() {
        //given
        UserEntity user = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        service.saveUser(user);

        //when
        Optional<UserEntity> returnedUser = service.blockUser(user.getId());

        //then
        assertTrue(returnedUser.map(UserEntity::getIsBlocked)
                .orElseThrow(() -> new IllegalStateException("Object returnedUser is empty.")));
    }

    @Test
    public void shouldRetrieveCartOwnedByNewlyCreatedUser() {
        //given
        UserEntity user = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        service.saveUser(user);

        //when
        Optional<CartEntity> retrievedCart = cartRepository.findById(user.getCart().getId());

        //then
        assertTrue(retrievedCart.isPresent());
    }

    @Test
    public void shouldDeleteCartWhenDeletingOwner() {
        //given
        UserEntity user = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        service.saveUser(user);
        long cartId = user.getCart().getId();

        //when
        repository.deleteById(user.getId());
        Optional<CartEntity> retrievedCart = cartRepository.findById(cartId);

        //then
        assertFalse(retrievedCart.isPresent());
    }

}
