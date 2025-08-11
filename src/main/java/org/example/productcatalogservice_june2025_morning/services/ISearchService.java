package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize);
}
