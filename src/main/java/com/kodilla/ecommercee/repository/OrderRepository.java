package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
