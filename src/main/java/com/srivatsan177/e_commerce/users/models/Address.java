package com.srivatsan177.e_commerce.users.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.srivatsan177.e_commerce.orders.models.Order;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String addressLine1;
    private String addressLine2;
    private int postalCode;
    private boolean inactive;

    @JsonBackReference
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;

    public Address() {
    }

    public Address(String addressLine1, int postalCode) {
        this.addressLine1 = addressLine1;
        this.postalCode = postalCode;
        inactive = false;
    }

    public Address(String addressLine1, String addressLine2, int postalCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        inactive = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
