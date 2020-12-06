package com.kodilla.ecommercee.domain;

import com.google.common.collect.Iterables;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    private UserEntity user1;
    private UserEntity user2;

    @Before
    public void setUp() {
        user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");
    }

    @After
    public void cleanUp() {
        try {
            List<UserEntity> savedUsers = repository.findAll();
            for (UserEntity user : savedUsers) {
                repository.deleteById(user.getUserId());
            }
        } catch (Exception e) {
            throw new IllegalStateException("The clean-up method in UserEntityTestSuite not executed properly.");
        }
    }

    @Test
    public void shouldSaveNewUsers() {
        //given & when
        service.saveUser(user1);
        service.saveUser(user2);

        //then
        assertEquals(2, Iterables.size(repository.findAll()));
    }

    @Test
    public void shouldRetrieveSavedUsers() {
        //given
        service.saveUser(user1);
        service.saveUser(user2);

        //when
        UserEntity retrievedUser1 = service.getUser(user1.getUserId()).orElse(null);
        UserEntity retrievedUser2 = service.getUser(user2.getUserId()).orElse(null);

        //then
        assertEquals(user1, retrievedUser1);
        assertEquals(user2, retrievedUser2);
    }

    @Test
    public void shouldRetrieveBlockedUser() {
        //given
        service.saveUser(user1);

        //when
        service.blockUser(user1.getUserId());
        UserEntity retrievedUser = service.getUser(user1.getUserId()).orElse(user2);

        //then
        assertEquals(false, user2.getIsBlocked());
        assertEquals(true, retrievedUser.getIsBlocked());
    }

    @Test
    public void shouldReturnBlockedUser() {
        //given
        service.saveUser(user1);

        //when
        UserEntity returnedUser = service.blockUser(user1.getUserId()).orElse(user2);

        //then
        assertEquals(false, user2.getIsBlocked());
        assertEquals(true, returnedUser.getIsBlocked());
    }

    @Test
    public void shouldDeleteUser() {
        //given
        service.saveUser(user1);
        service.saveUser(user2);

        //when
        repository.deleteById(user1.getUserId());

        //then
        assertEquals(1, Iterables.size(repository.findAll()));
    }

}
