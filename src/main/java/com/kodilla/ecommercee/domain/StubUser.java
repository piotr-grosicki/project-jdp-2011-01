package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stubUser")
public class StubUser {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "user_Id", unique = true)
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
