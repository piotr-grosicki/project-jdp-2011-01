package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllGroups")
    public List<GroupDto> getAllGroups(){
        return groupMapper.maptoProductGroupDtoList(groupService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto){
        groupService.saveProductGroup(groupMapper.mapToGroup(groupDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return groupMapper.mapToGroupDto(groupService.findGroupById(groupId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public void updateGroup(@RequestBody GroupDto groupDto) {
        groupService.saveProductGroup(groupMapper.mapToGroup(groupDto));
    }
}