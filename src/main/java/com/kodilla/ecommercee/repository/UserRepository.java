package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
