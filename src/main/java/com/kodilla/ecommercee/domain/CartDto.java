package com.kodilla.ecommercee.domain;

public class CartDto {
    private long cartId;
    private long userId;
    private String listOfProducts;
    public CartDto(final long cartId, final long userId, String listOfProducts) {
        this.cartId = cartId;
        this.userId = userId;
        this.listOfProducts = listOfProducts;
    }
    public long getCartId() {
        return cartId;
    }
    public long getUserId() {
        return userId;
    }
    public String getListOfProducts() {
        return listOfProducts;
    }
    public void setListOfProducts(String listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}


