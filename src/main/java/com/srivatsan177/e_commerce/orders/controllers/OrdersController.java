package com.srivatsan177.e_commerce.orders.controllers;

import com.srivatsan177.e_commerce.orders.models.Order;
import com.srivatsan177.e_commerce.orders.models.OrderPlaceRequest;
import com.srivatsan177.e_commerce.orders.repositories.OrderRepository;
import com.srivatsan177.e_commerce.products.models.ProductDetail;
import com.srivatsan177.e_commerce.users.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderPlaceRequest orderRequest) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setUuid(orderRequest.getProductDetailId());
        Address address = new Address();
        address.setId(orderRequest.getAddressId());
        Order order = new Order(productDetail, address);
        orderRepository.save(order);
        return new ResponseEntity<>("Order Placed", HttpStatus.OK);
    }
}
