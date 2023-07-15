package com.srivatsan177.e_commerce.store.models;

import com.srivatsan177.e_commerce.products.models.ProductDetail;
import com.srivatsan177.e_commerce.users.models.Address;
import com.srivatsan177.e_commerce.users.models.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String storeName;

    @OneToOne
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<ProductDetail> productDetailList;

    public Store() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
