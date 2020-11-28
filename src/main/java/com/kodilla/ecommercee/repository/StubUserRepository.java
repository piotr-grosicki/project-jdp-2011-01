package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.StubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StubUserRepository extends JpaRepository<StubUser, Long> {

}
