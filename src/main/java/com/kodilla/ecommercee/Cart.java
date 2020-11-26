package com.kodilla.ecommercee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
public class Cart {
    @Id
    @Column(name = "CART_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> products = new ArrayList<>();
}
