package com.kodilla.ecommercee.domain;

public class ProductDto {

    private Long id;
    private String productName;
    private String productDescription;
    private double productPrice;

    public ProductDto(Long id, String productName, String productDescription, double productPrice) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public ProductDto(){
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
