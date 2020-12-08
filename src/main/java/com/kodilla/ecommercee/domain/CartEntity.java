package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
@NoArgsConstructor
public final class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "CART_ID", unique = true)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_ProductEntity_CART",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<ProductEntity> products = new ArrayList<>();

    public CartEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setId(long cartId) {
        this.id = cartId;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}




