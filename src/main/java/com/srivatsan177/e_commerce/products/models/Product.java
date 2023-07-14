package com.srivatsan177.e_commerce.products.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(indexes = @Index(name = "product_index", columnList = "company, productName"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private String productS3ImagePath;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Product() {
    }

    public Product(long id, String company, String productName, String shortDescription, String productDescription, String productS3ImagePath, String productType) {
        this.id = id;
        this.company = company;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.productDescription = productDescription;
        this.productS3ImagePath = productS3ImagePath;
        this.productType = productType;
    }

    public void setProductS3ImagePath(String productS3ImagePath) {
        this.productS3ImagePath = productS3ImagePath;
    }

    public String getProductS3ImagePath() {
        return productS3ImagePath;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getProductType() {
        return productType;
    }

    @Column(nullable = false)
    private String productType;

    public void setId(long id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
