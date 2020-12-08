package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;

public class UserMapper {

    public UserEntity mapToUserEntity(final UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getEmail()
        );
    }

    public UserDto mapToUserDto(final UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getIsBlocked()
        );
    }
}
