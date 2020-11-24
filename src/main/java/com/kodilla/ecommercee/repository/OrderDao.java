package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order, Integer> {

}
