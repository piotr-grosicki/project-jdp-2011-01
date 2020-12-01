package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "USERS")
public final class UserEntity {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private List<OrderEntity> orders = new ArrayList<>();
    private CartEntity cartEntity = new CartEntity();


    public UserEntity(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true, nullable = false)
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

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
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