package com.srivatsan177.e_commerce.products.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.srivatsan177.e_commerce.orders.models.Order;
import com.srivatsan177.e_commerce.store.models.Store;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(indexes = @Index(name = "product_detail_index", columnList = "product_id"))
public class ProductDetail {
    @Id
    private String uuid;

    @Column(nullable = false)
    private double cost;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToOne(mappedBy = "productDetail")
    private Order order;

    @ManyToOne
    private Store store;


    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ProductDetail() {
    }

    public ProductDetail(String uuid, double cost, Product product) {
        this.uuid = uuid;
        this.cost = cost;
        this.product = product;
    }

    public double getCost() {
        return cost;
    }
}
