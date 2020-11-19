package com.kodilla.ecommercee.domain;


public class OrderDto {

    private Long id;
    private String orderName;
    private String orderContent;

    public OrderDto(Long id, String orderName, String orderContent) {
        this.id = id;
        this.orderName = orderName;
        this.orderContent = orderContent;
    }

    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderContent() {
        return orderContent;
    }
}


