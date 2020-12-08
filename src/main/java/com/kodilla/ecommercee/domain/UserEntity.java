package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "USERS")
public final class UserEntity {

    private Long userId;
    private String userName;

    private String userPassword;
    private String userEmail;
    private Boolean isBlocked;
    private List<OrderEntity> orders = new ArrayList<>();

    public UserEntity(/*Long userId, */String userName, String userPassword, String userEmail) {
        /*this.userId = userId;*/
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.isBlocked = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    public Long getUserId() {
        return userId;
    }

    @OneToMany(
            targetEntity = OrderEntity.class,
            mappedBy = "userEntity",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    public List<OrderEntity> getOrders() {
        return orders;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "USER_PASSWORD")
    public String getUserPassword() {
        return userPassword;
    }

    @Column(name = "USER_EMAIL")
    public String getUserEmail() {
        return userEmail;
    }

    @Column(name = "BLOCKED")
    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
