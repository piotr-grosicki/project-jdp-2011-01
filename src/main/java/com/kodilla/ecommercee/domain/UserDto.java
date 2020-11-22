package com.kodilla.ecommercee.domain;

public class UserDto {
    private Long id;
    private String userName;
    private String userSurname;
    private Boolean isBlocked;
    private String userKey;

    public UserDto(Long id, String userName, String userSurname, Boolean isBlocked, String userKey) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.isBlocked = isBlocked;
        this.userKey = userKey;
    }
}