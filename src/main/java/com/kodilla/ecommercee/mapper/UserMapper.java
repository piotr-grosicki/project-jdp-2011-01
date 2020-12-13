package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductEntity;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
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
                user.getUserEmail(),
                user.getIsBlocked()
        );
    }
}
