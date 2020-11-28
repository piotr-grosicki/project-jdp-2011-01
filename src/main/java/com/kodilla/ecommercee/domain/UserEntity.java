package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public final class UserEntity {
    private int userId;
    private List<OrderEntity> orders = new ArrayList<>();

    public UserEntity() {
    }

    @Id
    @GeneratedValue
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

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}