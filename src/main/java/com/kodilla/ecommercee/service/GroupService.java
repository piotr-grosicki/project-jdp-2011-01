package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.GroupEntity;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<GroupEntity> findAllProductGroups() {
        return groupRepository.findAll();
    }

    public GroupEntity findGroupById(Long groupId){
        return groupRepository.findById(groupId).orElse(null);
    }

    public GroupEntity saveProductGroup(final GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    public void deleteProductGroup(Long groupId){
        groupRepository.deleteById(groupId);
    }
}