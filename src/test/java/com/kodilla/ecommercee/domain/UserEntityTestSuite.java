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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        try {
            List<UserEntity> savedUsers = repository.findAll();
            for (UserEntity user : savedUsers) {
                repository.deleteById(user.getId());
            }
        } catch (Exception e) {
            throw new IllegalStateException("The clean-up method in UserEntityTestSuite not executed properly.");
        }
    }

    @Test
    public void shouldSaveNewUsers() {
        //given
        cleanUp();

        //when
        user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");

        //then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldRetrieveSavedUsers() {
        //given & when
        UserEntity retrievedUser1 = service.getUser(user1.getId()).orElse(null);
        UserEntity retrievedUser2 = service.getUser(user2.getId()).orElse(null);

        //then
        assertEquals(user1, retrievedUser1);
        assertEquals(user2, retrievedUser2);
    }

    @Test
    public void shouldRetrieveBlockedUser() {
        //given & when
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

    @Test
    public void shouldDeleteUser() {
        //given
        long countSavedUsersBeforeDeletion = repository.findAll().size();
        long deletedUserId = user1.getId();

        //when
        repository.deleteById(deletedUserId);
        UserEntity deletedUser = service.getUser(deletedUserId).orElse(null);

        //then
        assertEquals(countSavedUsersBeforeDeletion - 1, repository.findAll().size());
        assertNull(deletedUser);
    }

}
