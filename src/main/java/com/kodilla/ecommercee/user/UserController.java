package com.kodilla.ecommercee.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

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
    public String generateUserKey(@RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam String password) {
        return service.generateKey(name, surname, password);
    }

    @RequestMapping(method = GET, value = "/getAll")
    public List<UserDto> getAllUsers() {
        return mapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method = GET, value = "/get")
    public UserDto getUser(@RequestParam final Long userId) {
        return mapper.mapToUserDto(service.getUser(userId)
                .orElseThrow(() -> new NoSuchElementException("No user with id " + userId))
        );
    }

    @RequestMapping(method = PUT, value = "/update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUserEntity(userDto)));
    }

    @RequestMapping(method = DELETE, value = "/delete")
    public void deleteUser(@RequestParam Long userId) {
        try {
            service.deleteUser(userId);
        } catch (Exception e) {
            throw new NoSuchElementException("No user with id " + userId);
        }
    }

}
