package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "cart")
public class Cart {

    @Id
    @NotNull
    //@GeneratedValue(strategy = GenerationType.AUTO) very important issue
    private long cartId;
    private StubUser stubUser;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userIdFromStubUser")
    public StubUser getStubUser() {
        return stubUser;
    }
    public void setStubUser(StubUser stubUser) {
        this.stubUser = stubUser;
    }

    private List<StubProduct> stubProduct;
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_CART_PRODUCT",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    public List<StubProduct> getStubProduct() {
        return stubProduct;
    }
    public void setStubProduct(List<StubProduct> stubProduct) {
        this.stubProduct = stubProduct;
    }
}
