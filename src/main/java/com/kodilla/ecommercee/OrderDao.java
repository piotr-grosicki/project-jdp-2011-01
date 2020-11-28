package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<OrderEntity, Long> {
}
