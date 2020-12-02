package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ORDERS")
public final class OrderEntity {

    private int orderId;
    private Date dateOfOrder = new Date();
    private List<ProductEntity> products = new ArrayList<>();
    private UserEntity userEntity;

    public OrderEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID", unique = true, nullable = false)
    public int getOrderId() {
        return orderId;
    }

    @Column(name = "DATE_OF_ORDER", nullable = false)
    public Date getDateOfOrder() {
        return dateOfOrder;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
            )
            public List<ProductEntity> getProducts() {
            return products;
            }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    private void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private void setDateOfOrder(Date date_of_order) {
        this.dateOfOrder = date_of_order;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}