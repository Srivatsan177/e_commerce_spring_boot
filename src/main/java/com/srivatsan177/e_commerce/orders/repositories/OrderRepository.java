package com.srivatsan177.e_commerce.orders.repositories;

import org.springframework.data.repository.CrudRepository;
import com.srivatsan177.e_commerce.orders.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
