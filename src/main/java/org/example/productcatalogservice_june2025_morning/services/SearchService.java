package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.dtos.SortParam;
import org.example.productcatalogservice_june2025_morning.dtos.SortType;
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
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
//        Sort sortById = Sort.by("id").descending();
//        Sort sort = Sort.by("price").descending().and(sortById);

        Sort sort = null;
        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = Sort.by(sortParams.get(0).getSortCriteria());
            else
                sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();

            for(int i=1;i<sortParams.size();i++) {
                if(sortParams.get(i).getSortType().equals(SortType.ASC))
                   sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
                else
                    sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()).descending());
            }
        }
        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
