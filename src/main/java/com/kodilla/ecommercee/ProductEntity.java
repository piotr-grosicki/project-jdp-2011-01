package com.kodilla.ecommercee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private GroupEntity group;

    @Column(name = "NAME", nullable = false)
    private String productName;

    @Column(name = "DESCRIPTION")
    private String productDescription;

    @Column(name = "PRICE", nullable = false)
    private double productPrice;

    @ManyToMany
    @JoinTable(
            name = "JOIN_CART_PRODUCT",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    private List<CartEntity> carts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<OrderEntity> orders = new ArrayList<>();

    public ProductEntity(String productName, String productDescription, double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public ProductEntity() {
    }

    public Long getId() {
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

    public void setId(Long id) {
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