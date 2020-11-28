package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
public class CartEntity {
    @Id
    @Column(name = "CART_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<ProductEntity> products = new ArrayList<>();
}
