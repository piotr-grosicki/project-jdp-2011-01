package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS_OF_PRODUCTS")
public final class GroupEntity {
    @Id
    @Column(name = "GROUP_OF_PRODUCTS_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "LIST_OF_PRODUCTS")
    @OneToMany(
            targetEntity = ProductEntity.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductEntity> products =  new ArrayList<>();

    public GroupEntity(String groupName) {
        this.groupName = groupName;
    }

    public GroupEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}