package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.controller.GroupController;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupEntityTestSuite {
    @Autowired
    GroupRepository groupRepo;
    @Autowired
    GroupController groupController;
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    ProductRepository productRepo;

    @Test
    public void groupEntitySettersAndGettersTest() {
        //Given
        GroupEntity testGroup = new GroupEntity("test name");
        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, null);
        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, null);
        List<ProductEntity> productsList = new ArrayList<>(Arrays.asList(testProduct1, testProduct2));
        //When
        testGroup.setName("another name");
        testGroup.setProducts(productsList);
        //Then
        assertEquals("another name", testGroup.getName());
        assertEquals(2, testGroup.getProducts().size());
    }

    @Test
    public void shouldSaveAndReadSingleGroup() {
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
        groupRepo.deleteAll();
        productRepo.deleteAll();
        GroupEntity testGroup = new GroupEntity("test name");
        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, null);
        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, null);
        groupRepo.save(testGroup);
        Long id = testGroup.getId();
        String secondName = "new name";
        //When
        testProduct1.setGroup(testGroup);
        testProduct2.setGroup(testGroup);
        productRepo.save(testProduct1);
        productRepo.save(testProduct2);
        testGroup.setName(secondName);
        groupRepo.save(testGroup);
        Optional<GroupEntity> retrievedGroup = groupRepo.findById(id);
        //Then
        retrievedGroup.ifPresent(g -> assertEquals(2, g.getProducts().size()));
        retrievedGroup.ifPresent(g -> assertEquals(secondName, g.getName()));
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

//    @Test
//    public void shouldUpdateConnectedProducts() {
//        //Given
//        groupRepo.deleteAll();
//        productRepo.deleteAll();
//        GroupEntity testGroup = new GroupEntity("test name");
//        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, testGroup);
//        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, testGroup);
//        groupRepo.save(testGroup);
//        productRepo.save(testProduct1);
//        productRepo.save(testProduct2);
//        //When
//        testProduct1.setName("another name");
//        Optional<ProductEntity> renamedProduct = productRepo.findById(testProduct1.getId());
//        renamedProduct.ifPresent(p -> p.setName("another name"));
//        //Then
//        Optional<GroupEntity> group = groupRepo.findById(testGroup.getId());
//        group.ifPresent(g -> g.getProducts().forEach(e-> System.out.println(e.getName())));
////        group.ifPresent(g ->
////                assertTrue(g.getProducts().stream().anyMatch(p -> p.getName().equals("another name"))));
//    }

//    @Test
//    public void shouldRemoveConnectedProducts() {
//        //Given
//        groupRepo.deleteAll();
//        productRepo.deleteAll();
//        GroupEntity testGroup = new GroupEntity("test name");
//        groupRepo.save(testGroup);
//        ProductEntity testProduct1 = new ProductEntity("test name", "description", 1.0, null);
//        ProductEntity testProduct2 = new ProductEntity("test name", "description", 1.0, null);
//        testProduct1.setGroup(testGroup);
//        testProduct2.setGroup(testGroup);
//        productRepo.save(testProduct1);
//        productRepo.save(testProduct2);
//        //When
//        groupRepo.deleteById(testGroup.getId());
//        //Then
//        assertFalse(productRepo.existsById(testProduct1.getId()));
//        assertFalse(productRepo.existsById(testProduct2.getId()));
//    }
}
