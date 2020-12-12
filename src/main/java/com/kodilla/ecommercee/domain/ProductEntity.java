package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
public final class ProductEntity {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private GroupEntity group;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)

    private List<CartEntity> carts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<OrderEntity> orders = new ArrayList<>();

    public ProductEntity(Long id, String name, String description, double price, GroupEntity group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    public ProductEntity(String name, String description, double price, GroupEntity group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    public void addCart(CartEntity newCart) {
        this.carts.add(newCart);
    }

    public void addOrder(OrderEntity newOrder) {
        this.orders.add(newOrder);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public GroupEntity getGroup() {
        return group;
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

    public void setName(String productName) {
        this.name = productName;
    }

    public void setDescription(String productDescription) {
        this.description = productDescription;
    }

    public void setPrice(double productPrice) {
        this.price = productPrice;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}