package com.kodilla.ecommercee.domain;

import com.google.common.collect.Iterables;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    UserEntity user1;
    UserEntity user2;

    @Before
    public void setUp() {
        user1 = new UserEntity(1L, "John Smith", "password", "jsmith@gmail.com");
        user2 = new UserEntity(2L, "Jackie Brown", "password", "jbrown@gmail.com");
    }

    @Test
    public void shouldSaveNewUsers() {
        //given & when
        userRepository.save(user1);
        userRepository.save(user2);

        //then
        assertEquals(2, Iterables.size(userRepository.findAll()));

        //clean-up
        userRepository.deleteById(1L);
        userRepository.deleteById(2L);
    }

}
