package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartDao extends CrudRepository<CartEntity, Long> {

}
