package com.kodilla.ecommercee.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<UserEntity> getUser(Long id) {
        return repository.findById(id);
    }

    public UserEntity saveUser(UserEntity user) {
        return repository.save(user);
    }

    public Optional<UserEntity> blockUser(Long id) {
        Optional<UserEntity> userOptional = getUser(id);
        userOptional.ifPresent(user -> {
            user.setBlocked(true);
            saveUser(user);
        });
        return userOptional;
    }

    //generates a 16-sign random key
    //user authorization not implemented yet
    public String generateKey(String name, String surname, String password) {
        String toHexString = Long.toHexString(new SecureRandom().nextLong());
        return ("0000000000000000" + toHexString).substring(toHexString.length());
    }

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
