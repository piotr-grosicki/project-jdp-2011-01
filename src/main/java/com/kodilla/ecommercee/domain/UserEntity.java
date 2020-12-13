package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "USERS")
public final class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BLOCKED")
    private Boolean isBlocked;


    @OneToMany(
            targetEntity = OrderEntity.class,
            mappedBy = "userEntity",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private CartEntity cart;

    public UserEntity(String userName, String userPassword, String userEmail) {
        this.name = userName;
        this.password = userPassword;
        this.email = userEmail;
        this.isBlocked = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}
