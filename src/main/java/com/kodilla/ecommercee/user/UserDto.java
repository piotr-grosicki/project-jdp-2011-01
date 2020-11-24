package com.kodilla.ecommercee.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String userSurname;
    private Boolean isBlocked;
    private String userKey;

}
