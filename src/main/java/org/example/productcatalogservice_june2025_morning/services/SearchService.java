package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.models.Product;
import org.example.productcatalogservice_june2025_morning.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize) {
        Sort sortById = Sort.by("id").descending();
        Sort sort = Sort.by("price").descending().and(sortById);
        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
