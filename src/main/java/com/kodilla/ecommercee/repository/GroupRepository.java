package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    @Override
    Optional<GroupEntity> findById(Long id);
}