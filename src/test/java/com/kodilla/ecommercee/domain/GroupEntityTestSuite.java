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
    public void groupEntityGeneratesId() {
        //Given
        groupRepo.deleteAll();
        GroupEntity testGroup1 = new GroupEntity("test name");
        GroupEntity testGroup2 = new GroupEntity("test name");
        //When
        groupRepo.save(testGroup1);
        groupRepo.save(testGroup2);
        //Then
        assertTrue(testGroup1.getId() != null && testGroup2.getId() != null);
        assertNotEquals(testGroup1.getId(), testGroup2.getId());
    }

//    @Test
//    public void groupEntitySettersAndGettersTest() {
//        //Given
//        groupRepo.deleteAll();
//        GroupEntity testGroup = new GroupEntity("test name");
//        groupRepo.save(testGroup);
//        Long id = testGroup.getId();
//        //When
//        groupRepo.findById(id).ifPresent(group -> System.out.println("TEST: "+group.getTest().toString()));
//        groupRepo.findById(id).ifPresent(group -> System.out.println("TEST: "+(group.getTest()!=null)));
//        //Then
//
//    }

    @Test
    public void shouldCreateAndReadSingleGroup() {
        //Given
        groupRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        //When&Then
        groupRepo.save(testGroup);
        assertTrue(groupRepo.existsById(testGroup.getId()));
        Optional<GroupEntity> receivedGroup = groupRepo.findById(testGroup.getId());
        assertTrue(receivedGroup.isPresent());
        receivedGroup.ifPresent(group -> assertEquals("test name", group.getName()));
    }

    @Test
    public void shouldUpdateExistingGroup() {
        //Given
        GroupEntity testGroup = new GroupEntity("test name");
        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, null);
        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, null);
        groupRepo.save(testGroup);
        Long id = testGroup.getId();
        String entryName = "test name";
        //When
        testGroup.setName("another name");
        testProduct1.setGroup(testGroup);
        testProduct2.setGroup(testGroup);
        testGroup.getProducts();
        //Then
        Optional<GroupEntity> receivedGroup = groupRepo.findById(id);
        assertTrue(receivedGroup.isPresent());
        receivedGroup.ifPresent(group -> assertEquals("another name", group.getName()));
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
        groupRepo.save(testGroup);
        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, null);
        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, null);
        testProduct1.setGroup(testGroup);
        testProduct2.setGroup(testGroup);
        productRepo.save(testProduct1);
        productRepo.save(testProduct2);
        //When
//        groupRepo.deleteById(testGroup.getId());
        //Then
        assertTrue(productRepo.existsById(testProduct1.getId()));
        assertTrue(productRepo.existsById(testProduct2.getId()));
    }
}
