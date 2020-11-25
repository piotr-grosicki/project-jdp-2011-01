package com.kodilla.ecommercee.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public final class User {
    private int userID;
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    public int getUserID() {
        return userID;
    }

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setUserID(int user_id) {
        this.userID = user_id;
    }

    public void setOrders(List<Order> order_id) {
        this.orders = order_id;
    }
}
