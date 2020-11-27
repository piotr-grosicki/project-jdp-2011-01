package com.kodilla.ecommercee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS_OF_PRODUCTS")
public class GroupEntity {
    @Id
    @Column(name = "GROUP_OF_PRODUCTS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(
            targetEntity = ProductEntity.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductEntity> products = new ArrayList<>();
}
