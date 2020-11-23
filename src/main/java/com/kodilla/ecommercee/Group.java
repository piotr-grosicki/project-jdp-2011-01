package com.kodilla.ecommercee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Table(name = "GROUP_OF_PRODUCTS")
public class Group {
    private Long id;
    private String groupName;
    private List<Product> listOfProducts = new ArrayList<>();

    public Group(Long id, String groupName, List<Product> listOfProducts) {
        this.id = id;
        this.groupName = groupName;
        this.listOfProducts = listOfProducts;
    }

    public Group() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }

    @Column(name = "LIST_OF_PRODUCTS")
    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "productGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}