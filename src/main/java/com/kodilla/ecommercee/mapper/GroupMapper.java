package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    @Autowired
    private ProductMapper productMapper;

    public GroupEntity mapToGroup(final GroupDto groupDto) {
        return new GroupEntity(
                groupDto.getName(),
                productMapper.mapToEntitiesList(groupDto.getProductDtos())
        );
    }

    public GroupDto mapToGroupDto(final GroupEntity groupEntity) {
        return new GroupDto(
                groupEntity.getId(),
                groupEntity.getName(),
                productMapper.mapToDtoList(groupEntity.getProducts())
        );
    }

    public List<GroupDto> maptoProductGroupDtoList(final List<GroupEntity> groupEntityList) {
        return groupEntityList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}