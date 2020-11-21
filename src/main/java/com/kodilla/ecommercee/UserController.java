package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private UserDto userDto;

    @RequestMapping(method = RequestMethod.GET, value = "getAllUsers")
    public List<UserDto> getAllUsers(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){

    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId){
        return new UserDto(1L, "Name", "Surname", false, "123");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUserById(@RequestParam Long userId){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return new UserDto(1L, "Name", "Surname", false, "123");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "blockUser")
    public UserDto blockUser(@RequestParam Long userId){
        return new UserDto(1l, "Name", "Surname", false, "123");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUserKey")
    public String createUserKey(@RequestParam Long userId) {
        return "123";
    }
}