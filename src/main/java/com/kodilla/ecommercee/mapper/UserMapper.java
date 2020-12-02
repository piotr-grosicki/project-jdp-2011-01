package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;

public class UserMapper {

    public UserEntity mapToUserEntity(final UserDto userDto) {
        return new UserEntity(
                userDto.getUserName(),
                userDto.getUserPassword(),
                userDto.getUserEmail()
        );
    }

    public UserDto mapToUserDto(final UserEntity user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserEmail()
        );
    }

}
