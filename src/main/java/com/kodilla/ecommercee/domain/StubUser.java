package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stubUser")
public class StubUser {
    @Id
    @NotNull
    private Long id;
    @NotNull
    @Column(name = "userName")
    private String userName;
    @NotNull
    @Column(name = "userSurname")
    private String userSurname;
    @NotNull
    @Column(name = "isBlocked")
    private Boolean isBlocked;
    @NotNull
    @Column(name = "userKey")
    private String userKey;
}
