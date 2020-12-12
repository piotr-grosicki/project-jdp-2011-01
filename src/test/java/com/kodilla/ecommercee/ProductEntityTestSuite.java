package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupEntity;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserEntity;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductEntityTestSuite {
    @Autowired
    ProductMapper productMapper;

    @Test
    public void shouldConvertEntitiesListToDtoList() {
        //Given
        GroupEntity group = new GroupEntity("name");
        ProductEntity product1 = new ProductEntity("name1", "description2", 1.0, group);
        ProductEntity product2 = new ProductEntity("name2", "description2", 1.0, group);
        List<ProductEntity> entitiesList = new ArrayList<>();
        entitiesList.add(product1);
        entitiesList.add(product2);
        //When
        List<ProductDto> dtosList = productMapper.mapToDtoList(entitiesList);
        //Then
        Assert.assertEquals(2, dtosList.size());
        Assert.assertEquals("name2", dtosList.get(1).getName());
        Assert.assertEquals("description2", dtosList.get(1).getDescription());
        Assert.assertEquals(1.0, dtosList.get(1).getPrice(), 0);
        Assert.assertEquals(group.getId(), dtosList.get(1).getGroupId());
    }
}
