package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GroupDto {
    private Long id;
    private String groupName;
    private List<ProductDto> productDtoList;

    public GroupDto(Long id, String name, List<ProductEntity> products) {
    }
}
