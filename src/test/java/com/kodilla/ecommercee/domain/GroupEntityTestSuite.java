package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupEntityTestSuite {
    @Autowired
    GroupRepository groupRepo;

    @Test
    public void shouldCreateAndReadSingleGroup() {
        //Given
        groupRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        //When
        groupRepo.save(testGroup);
        System.out.println(testGroup.getId());
        //Then
        Assert.assertEquals(1, groupRepo.count());
//        Assert.assertEquals("test name", groupRepo.findById(1L));
    }

    @Test
    public void shouldRemoveSingleGroup() {
        //Given

        //When

        //Then

    }

    @Test
    public void shouldNotRemoveConnectedProduct() {
        //Given

        //When

        //Then

    }

    @Test
    public void shouldNotRemoveAnyOtherEntity() {
        //Given

        //When

        //Then

    }
}
