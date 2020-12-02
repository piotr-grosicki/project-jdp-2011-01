package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private UserService service;
    private UserMapper mapper;

    @RequestMapping(method = POST, value = "/create", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(mapper.mapToUserEntity(userDto));
    }

    @RequestMapping(method = PATCH, value = "/block")
    public UserDto blockUser(@RequestParam Long userId) {
        return mapper.mapToUserDto(service.blockUser(userId)
                .orElseThrow(() -> new NoSuchElementException("No user with id " + userId)));
    }

    @RequestMapping(method = POST, value = "/generateKey")
    public String generateUserKey(@RequestParam Long userId) {
        return service.generateKey(userId);
    }

}
