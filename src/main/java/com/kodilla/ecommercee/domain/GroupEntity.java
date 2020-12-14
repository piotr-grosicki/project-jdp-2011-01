package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS_OF_PRODUCTS")
@NoArgsConstructor
public final class GroupEntity {

    public GroupEntity(String name, List<ProductEntity> products) {
        this.name = name;
        this.products = products;
    }

    @Id
    @Column(name = "GROUP_OF_PRODUCTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GROUP_NAME")
    private String name;

    @Column(name = "LIST_OF_PRODUCTS")
    @OneToMany(
            targetEntity = ProductEntity.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductEntity> products = new ArrayList<>();

    public GroupEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String groupName) {
        this.name = groupName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}