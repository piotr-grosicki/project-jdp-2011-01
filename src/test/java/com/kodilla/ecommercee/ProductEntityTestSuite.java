package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupEntity;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTestSuite {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void shouldConvertEntitiesListToDtosList() {
        //Given
        GroupEntity group = new GroupEntity("name");
        ProductEntity product1 = new ProductEntity("name1", "description2", 1.0, group);
        ProductEntity product2 = new ProductEntity("name2", "description2", 1.0, group);
        List<ProductEntity> entitiesList = new ArrayList<>(Arrays.asList(product1, product2));
        //When
        List<ProductDto> dtosList = productMapper.mapToDtoList(entitiesList);
        //Then
        Assert.assertEquals(2, dtosList.size());
        Assert.assertEquals("name2", dtosList.get(1).getName());
        Assert.assertEquals("description2", dtosList.get(1).getDescription());
        Assert.assertEquals(1.0, dtosList.get(1).getPrice(), 0);
        Assert.assertEquals(group.getId(), dtosList.get(1).getGroupId());
    }

    @Test
    public void shouldConvertDtosListToEntitiesList() {
        //Given
        GroupEntity group = new GroupEntity("name");
        groupRepository.save(group);
        ProductEntity product1 = new ProductEntity("name1", "description2", 1.0, group);
        ProductEntity product2 = new ProductEntity("name2", "description2", 1.0, group);
        ProductDto dto1 = productMapper.mapToDto(product1);
        ProductDto dto2 = productMapper.mapToDto(product2);
        List<ProductDto> dtosList = new ArrayList<>(Arrays.asList(dto1, dto2));
        //When
        List<ProductEntity> entitiesList = productMapper.mapToEntitiesList(dtosList);
        //Then
        Assert.assertEquals(2, entitiesList.size());
        Assert.assertEquals("name2", entitiesList.get(1).getName());
        Assert.assertEquals("description2", entitiesList.get(1).getDescription());
        Assert.assertEquals(1.0, entitiesList.get(1).getPrice(), 0);
        Assert.assertEquals(group.getId(), entitiesList.get(1).getGroup().getId());
    }
}
