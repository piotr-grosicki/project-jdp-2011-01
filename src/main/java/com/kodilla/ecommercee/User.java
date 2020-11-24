package com.kodilla.ecommercee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="user")
public class User {

    public User(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "password")
    private String userPassword;

    @Column(name = "email")
    private String userEmail;

    public User(){}

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    private Cart cart;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="Card")
}
