package com.kodilla.ecommercee.user;

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

}
