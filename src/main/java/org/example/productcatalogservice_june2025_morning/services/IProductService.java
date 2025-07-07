package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProductDetails();

    Product getProductById(Long productId);

    Product createProduct(Product input);

    Product replaceProduct(Long id,Product input);
}
