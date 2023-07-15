package com.srivatsan177.e_commerce.orders.models;

public class OrderPlaceRequest {
    private String productDetailId;
    private long addressId;

    public OrderPlaceRequest() {
    }

    public OrderPlaceRequest(String productDetailId, long addressId) {
        this.productDetailId = productDetailId;
        this.addressId = addressId;
    }

    public String getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
