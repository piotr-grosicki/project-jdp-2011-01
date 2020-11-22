package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String userSurname;
    private Boolean isBlocked;
    private String userKey;
}