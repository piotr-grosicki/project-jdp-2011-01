package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "PRODUCT")
public final class ProductEntity {

    private int productId;
    private List<OrderEntity> orders = new ArrayList<>();

    public ProductEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}