package com.kodilla.ecommercee;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String productName;
    @Column(name = "description")
    private String productDescription;
    @NotNull
    @Column(name = "price")
    private double productPrice;

    public Product(String productName, String productDescription, double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public Product() {
    }
}