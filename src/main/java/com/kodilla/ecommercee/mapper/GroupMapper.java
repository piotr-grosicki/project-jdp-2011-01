package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.GroupEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public GroupEntity mapToGroup(final GroupDto groupDto) {
        return new GroupEntity(
                groupDto.getId(),
                groupDto.getGroupName(),
                groupDto.getProducts()
        );
    }

    public GroupDto mapToGroupDto(final GroupEntity groupEntity) {
        return new GroupDto(
                groupEntity.getId(),
                groupEntity.getName(),
                groupEntity.getProductDtos()
        );
    }

    public List<GroupDto> maptoProductGroupDtoList(final List<GroupEntity> groupEntityList) {
        return groupEntityList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}