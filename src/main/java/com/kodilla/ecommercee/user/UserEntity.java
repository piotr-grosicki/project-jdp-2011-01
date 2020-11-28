package com.kodilla.ecommercee.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String userName;

    @Column(name = "lastname")
    private String userSurname;

    @Column(name = "blocked")
    private Boolean isBlocked;

    @Column(name = "user_key")
    private String userKey;

}
