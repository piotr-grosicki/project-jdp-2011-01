package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public final class OrderEntity {

    private Long orderId;
    private Date orderDate;
    private List<ProductEntity> products = new ArrayList<>();
    private UserEntity userEntity;

    public OrderEntity(Date orderDate, List<ProductEntity> products, UserEntity userEntity) {
        this.orderDate = orderDate;
        this.products = products;
        this.userEntity = userEntity;
    }

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID", unique = true, nullable = false)
    public Long getOrderId() {
        return orderId;
    }

    @Column(name = "DATE_OF_ORDER", nullable = false)
    public Date getOrderDate() {
        return orderDate;
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

    private void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    private void setOrderDate(Date date_of_order) {
        this.orderDate = date_of_order;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
