package com.kodilla.ecommercee.domain;

public class GroupDto{
    private Long id;
    private String groupName;

    public GroupDto(Long id, String groupName){
        this.id = id;
        this.groupName = groupName;
    }

    public GroupDto(){
    }

    public Long getId(){
        return id;
    }

    public String getGroupName(){
        return groupName;
    }
}