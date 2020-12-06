package com.kodilla.ecommercee.domain;

import com.google.common.collect.Iterables;
import com.kodilla.ecommercee.repository.UserRepository;
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
    private UserRepository userRepository;

    private UserEntity user1;
    private UserEntity user2;

    @Before
    public void setUp() {
        user1 = new UserEntity("John Smith", "password", "jsmith@gmail.com");
        user2 = new UserEntity("Jackie Brown", "password", "jbrown@gmail.com");
    }

    @After
    public void cleanUp() {
        List<UserEntity> savedUsers = userRepository.findAll();
        for (UserEntity user : savedUsers) {
            userRepository.deleteById(user.getUserId());
        }
    }

    @Test
    public void shouldSaveNewUsers() {
        //given & when
        userRepository.save(user1);
        userRepository.save(user2);

        //then
        assertEquals(2, Iterables.size(userRepository.findAll()));
    }

    @Test
    public void shouldRetrieveSavedUsers() {
        //given
        userRepository.save(user1);
        userRepository.save(user2);

        //when
        UserEntity retrievedUser1 = userRepository.findById(user1.getUserId()).orElse(null);
        UserEntity retrievedUser2 = userRepository.findById(user2.getUserId()).orElse(null);

        //then
        assertEquals(user1, retrievedUser1);
        assertEquals(user2, retrievedUser2);
    }

}
