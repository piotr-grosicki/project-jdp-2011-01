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
    @Column(name = "ID")
    private long id;

   @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "JOIN_ProductEntity_CART",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<ProductEntity> products = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private UserEntity owner;

    public long getId() {
        return id;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setId(long cartId) {
        this.id = cartId;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}




