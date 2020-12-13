package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupEntityTestSuite {
    @Autowired
    GroupRepository groupRepo;
    @Autowired
    ProductRepository productRepo;

    @Test
    public void shouldSaveAndReadSingleGroup() {
        //Given
        groupRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        //When
        groupRepo.save(testGroup);
        Optional<GroupEntity> receivedGroup = groupRepo.findById(testGroup.getId());
        //Then
        assertTrue(groupRepo.existsById(testGroup.getId()));
        assertTrue(receivedGroup.isPresent());
        receivedGroup.ifPresent(group -> assertEquals("test name", group.getName()));
    }

    @Test
    public void shouldRemoveSingleGroup() {
        //Given
        groupRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        groupRepo.save(testGroup);
        //When
        groupRepo.deleteById(testGroup.getId());
        //Then
        assertFalse(groupRepo.existsById(testGroup.getId()));
    }

    @Test
    public void shouldNotRemoveConnectedProducts() {
        //Given
        groupRepo.deleteAll();
        productRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, testGroup);
        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, testGroup);
        groupRepo.save(testGroup);
        productRepo.save(testProduct1);
        productRepo.save(testProduct2);
        //When
        groupRepo.deleteById(testGroup.getId());
        //Then
        assertTrue(productRepo.existsById(testProduct1.getProductId()));
        assertTrue(productRepo.existsById(testProduct2.getProductId()));
    }
}
