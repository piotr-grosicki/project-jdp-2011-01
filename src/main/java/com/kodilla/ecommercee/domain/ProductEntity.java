package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "PRODUCTS")
@NoArgsConstructor
public final class ProductEntity {
    public ProductEntity(String productName, String productDescription, double productPrice, GroupEntity group) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.group = group;
    }

    @Id
    @Column(name = "PRODUCT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String productName;

    @Column(name = "DESCRIPTION")
    private String productDescription;

    @Column(name = "PRICE", nullable = false)
    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private GroupEntity group;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "productEntities")
    private List<CartEntity> carts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<OrderEntity> orders = new ArrayList<>();

    public void addCart(CartEntity newCart) {
        this.carts.add(newCart);
    }

    public void addOrder(OrderEntity newOrder) {
        this.orders.add(newOrder);
    }

    public Long getProductId() {
        return id;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setProductId(Long id) {
        this.id = id;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}