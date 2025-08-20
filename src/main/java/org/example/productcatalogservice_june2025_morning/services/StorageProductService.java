package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.dtos.UserDto;
import org.example.productcatalogservice_june2025_morning.models.Product;
import org.example.productcatalogservice_june2025_morning.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getAllProductDetails() {
       return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if(productOptional.isPresent()) return productOptional.get();

        return null;
    }

    @Override
    public Product createProduct(Product input) {
        Optional<Product> productOptional = productRepo.findById(input.getId());
        if(productOptional.isPresent()) return productOptional.get();

        return productRepo.save(input);
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        return productRepo.save(input);
    }

    @Override
    public Product getProductBasedOnUserScope(Long productId, Long userId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if(productOptional.isPresent()) {
            //Call User Service
            UserDto userDto = restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class,userId).getBody();
            if(userDto!=null) {
                System.out.println(userDto.getEmail());
                return productOptional.get();
            }
        }
        return null;
    }
}
