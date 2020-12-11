package com.kodilla.ecommercee.domain;

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
        try {
            service.saveUser(user1);
            service.saveUser(user2);
        } catch (Exception e) {
            throw new IllegalStateException("The set-up method in UserEntityTestSuite not executed properly.");
        }
    }

    @After
    public void cleanUp() {
        for (UserEntity user : repository.findAll())
            repository.deleteById(user.getId());
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
        UserEntity retrievedUser = service.getUser(user1.getId()).orElse(user2);

        //then
        assertEquals(false, user2.getIsBlocked());
        assertEquals(true, retrievedUser.getIsBlocked());
    }

    @Test
    public void shouldReturnBlockedUser() {
        //given & when
        UserEntity returnedUser = service.blockUser(user1.getId()).orElse(user2);

        //then
        assertEquals(false, user2.getIsBlocked());
        assertEquals(true, returnedUser.getIsBlocked());
    }

}
