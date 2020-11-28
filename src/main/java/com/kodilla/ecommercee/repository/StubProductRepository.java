package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.StubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StubProductRepository extends JpaRepository<StubProduct, Long> {
}
