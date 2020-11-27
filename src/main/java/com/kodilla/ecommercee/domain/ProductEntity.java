package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "PRODUCTS")
public final class ProductEntity {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private GroupEntity group;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<OrderEntity> orders = new ArrayList<>();

    public ProductEntity() {
    }

    public Long getProductId() {
        return id;
    }

    public GroupEntity getGroup() {
        return group;
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

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}