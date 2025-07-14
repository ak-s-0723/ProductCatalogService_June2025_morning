package org.example.productcatalogservice_june2025_morning.repos;

import org.example.productcatalogservice_june2025_morning.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testQueries() {
//      List<Product> productList = productRepo.findAllByOrderByPriceDesc();
//      for(Product product : productList) {
//          System.out.println(product.getPrice());
//      }
        System.out.println(productRepo.getMeDescriptionForProductId(2L));
    }


}