package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "stubProduct")
public class StubProduct {
    private List<Cart> cart = new ArrayList<>();
    public StubProduct(@NotNull String productName, @NotNull String productDescription, @NotNull double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    @NotNull
    @Column(name = "productName")
    private String productName;
    @NotNull
    @Column(name = "productDescription")
    private String productDescription;
    @NotNull
    @Column(name = "productPrice")
    private double productPrice;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companies")
    public List<Cart> getCart() {
        return cart;
    }
    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
