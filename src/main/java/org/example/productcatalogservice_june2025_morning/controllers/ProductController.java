package org.example.productcatalogservice_june2025_morning.controllers;

import org.example.productcatalogservice_june2025_morning.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController    //Bean or singleton object
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");
        products.add(product);
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(name="id") Long productId) {
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

    //ToDo: Add Api Wrapper for Delete anD Put

}
