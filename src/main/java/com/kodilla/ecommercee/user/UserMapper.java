package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public UserEntity mapToUserEntity(final UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getUserSurname(),
                userDto.getIsBlocked(),
                userDto.getUserKey()
        );
    }

    public UserDto mapToUserDto(final UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getUserSurname(),
                user.getIsBlocked(),
                user.getUserKey()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<UserEntity> users) {
        return users.stream()
                .map(user -> new UserDto(
                                user.getId(),
                                user.getUserName(),
                                user.getUserSurname(),
                                user.getIsBlocked(),
                                user.getUserKey()
                        )
                )
                .collect(Collectors.toList());
    }

}
