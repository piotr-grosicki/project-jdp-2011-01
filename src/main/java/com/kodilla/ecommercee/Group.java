package com.kodilla.ecommercee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS_OF_PRODUCTS")
public class Group {
    @Id
    @Column(name = "GROUP_OF_PRODUCTS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();
}
