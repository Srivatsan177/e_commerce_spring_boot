package com.srivatsan177.e_commerce.products.repositories;

import com.srivatsan177.e_commerce.products.models.Product;
import com.srivatsan177.e_commerce.products.models.ProductDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
