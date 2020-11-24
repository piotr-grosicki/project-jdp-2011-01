package com.kodilla.ecommercee.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public final class User {
    private int user_id;
    private List<Order> order_id = new ArrayList<>();

    public User() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    public int getUser_id() {
        return user_id;
    }

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Order> getOrder_id() {
        return order_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setOrder_id(List<Order> order_id) {
        this.order_id = order_id;
    }
}
