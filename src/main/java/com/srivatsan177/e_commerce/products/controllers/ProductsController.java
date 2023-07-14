package com.srivatsan177.e_commerce.products.controllers;

import com.srivatsan177.e_commerce.products.models.Product;
import com.srivatsan177.e_commerce.products.models.ProductDetail;
import com.srivatsan177.e_commerce.products.repositories.ProductDetailRepository;
import com.srivatsan177.e_commerce.products.repositories.ProductRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;
    @GetMapping
    public Iterable<Product> home() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }
    @RolesAllowed(value = {"ROLE_ADMIN"})
    @PostMapping("/product-details")
    public List<ProductDetail> addProductDetails(@RequestBody List<ProductDetail> productDetails) {
        productDetails.forEach(productDetail -> productDetailRepository.save(productDetail));
        return productDetails;
    }
}
