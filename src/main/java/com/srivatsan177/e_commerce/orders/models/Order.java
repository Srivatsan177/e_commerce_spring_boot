package com.srivatsan177.e_commerce.orders.models;

import com.srivatsan177.e_commerce.orders.constants.OrderStatus;
import com.srivatsan177.e_commerce.products.models.Product;
import com.srivatsan177.e_commerce.products.models.ProductDetail;
import com.srivatsan177.e_commerce.users.models.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name="product_detail_uuid", referencedColumnName = "uuid")
    private ProductDetail productDetail;
    @ManyToOne
    private Address address;
    private OrderStatus status;

    public Order() {

    }

    public Order(ProductDetail productDetail, Address address) {
        this.productDetail = productDetail;
        this.address = address;
        status = OrderStatus.ORDER_PLACED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
