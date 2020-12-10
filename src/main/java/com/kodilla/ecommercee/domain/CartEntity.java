package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
public final class CartEntity {
    private long cartId;
    private UserEntity userEntity;
    private List<ProductEntity> productEntities = new ArrayList<>();

    public CartEntity(long cartId) {
        this.cartId = cartId;
    }

    public CartEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "CART_ID", unique = true)
    public long getCartId() {
        return cartId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    @ManyToMany
    @JoinTable(name = "JOIN_ProductEntity_CART",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}




