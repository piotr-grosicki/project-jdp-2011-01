package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER")
public final class Order {

    private int order_id;
    private Date date_of_order;
    private User user_id;
    private List<Product> products = new ArrayList<>();

    public Order(int order_id) {
        this.order_id = order_id;
        this.date_of_order = new Date();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    public int getOrder_id() {
        return order_id;
    }

    @NotNull
    @Column(name = "DATE_OF_ORDER")
    public Date getDate_of_order() {
        return date_of_order;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUserId() {
        return user_id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ORDER_ID_FOR_PRODUCT",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
            }
    )
    public List<Product> getProducts() {
        return products;
    }

    private void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    private void setDate_of_order(Date date_of_order) {
        this.date_of_order = date_of_order;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}
