package com.kodilla.ecommercee.domain;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "cart")
public class CartEntity {
    private long cartId;
    private StubUser stubUser;
    private List<StubProduct> stubProduct = new ArrayList<>();
    public CartEntity(long cartId) {
        this.cartId = cartId;
    }
    public CartEntity() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @NotNull
    @Column (name = "cart_Id", unique= true)
    public long getCartId() {
        return cartId;
    }
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stub_User_Id")
    public StubUser getStubUser() {
        return stubUser;
    }
    public void setStubUser(StubUser stubUser) {
        this.stubUser = stubUser;
    }

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_stubProduct_cart",
            joinColumns = {@JoinColumn(name = "cart_Id", referencedColumnName = "cart_Id")},
            inverseJoinColumns = {@JoinColumn(name = "stubProduct_Id", referencedColumnName = "stubProduct_Id")}
    )
    public List<StubProduct> getStubProduct() {
        return stubProduct;
    }
    public void setStubProduct(List<StubProduct> stubProduct) {
        this.stubProduct = stubProduct;
    }
}



