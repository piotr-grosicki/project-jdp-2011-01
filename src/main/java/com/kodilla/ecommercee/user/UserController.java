package com.kodilla.ecommercee.user;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private UserService service;

    @RequestMapping(method = GET, value = "getAllUsers")
    public List<UserDto> getAllUsers(){
        return service.getAllUsers();
    }

    @RequestMapping(method = GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId){
        return new UserDto(1L, "Name", "Surname", false, "123");
    }

    @RequestMapping(method = POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){

    }

    @RequestMapping(method = PUT, value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return new UserDto(1L, "Name", "Surname", false, "123");
    }

    @RequestMapping(method = PATCH, value = "blockUser")
    public UserDto blockUser(@RequestParam Long userId){
        return new UserDto(1l, "Name", "Surname", true, "123");
    }

    @RequestMapping(method = DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId){

    }

    @RequestMapping(method = POST, value = "createUserKey")
    public String createUserKey(@RequestParam(name = "id") Long userId) {
        return "123";
    }
}
