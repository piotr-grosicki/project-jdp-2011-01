package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER")
public final class Order {

    private int orderId;
    private Date dateOfOrder;
    private User userId;
    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    public int getOrderId() {
        return orderId;
    }

    @NotNull
    @Column(name = "DATE_OF_ORDER")
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUserId() {
        return userId;
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

    private void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    private void setDateOfOrder(Date date_of_order) {
        this.dateOfOrder = date_of_order;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
