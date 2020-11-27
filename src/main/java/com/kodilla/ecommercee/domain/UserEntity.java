package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public final class UserEntity {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private List<OrderEntity> orders = new ArrayList<>();
    private CartEntity cartEntity;

    public UserEntity() {
    }

    public UserEntity(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true, nullable = false)
    @NotNull
    public int getUserId() {
        return userId;
    }

    @OneToMany(
            targetEntity = OrderEntity.class,
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    public CartEntity getCartEntity() {
        return cartEntity;
    }

    @Column(name = "USER_NAME")
    public String getUserName(){
        return userName;
    }

    @Column(name = "USER_PASSWORD")
    public String getUserPassword(){
        return userPassword;
    }

    @Column(name = "USER_EMAIL")
    public String getUserEmail(){
        return userEmail;
    }

    public void setCardEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}