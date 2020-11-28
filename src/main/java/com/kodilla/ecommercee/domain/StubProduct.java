package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Component
@Getter
@NoArgsConstructor
@Entity
@Table(name = "stubProduct")
public class StubProduct {


    public StubProduct(@NotNull String productName, @NotNull String productDescription, @NotNull double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "stubProduct_Id")
    private Long id;
    @NotNull
    @Column(name = "stubProduct_Name")
    private String productName;
    @NotNull
    @Column(name = "stubProduct_Description")
    private String productDescription;
    @NotNull
    @Column(name = "stubProduct_Price")
    private double productPrice;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "stubProduct")
    private List<CartEntity> carts = new ArrayList<>();
    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }
}
