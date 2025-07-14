package org.example.productcatalogservice_june2025_morning.controllers;

import org.example.productcatalogservice_june2025_morning.dtos.CategoryDto;
import org.example.productcatalogservice_june2025_morning.dtos.ProductDto;
import org.example.productcatalogservice_june2025_morning.models.Category;
import org.example.productcatalogservice_june2025_morning.models.Product;
import org.example.productcatalogservice_june2025_morning.services.FakeStoreProductService;
import org.example.productcatalogservice_june2025_morning.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController    //Bean or singleton object
public class ProductController {

       @Autowired
       private IProductService productService;


//       public ProductController(IProductService productService) {
//           this.productService = productService;
//       }


    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProductDetails();
        for(Product product : products) {
          ProductDto productDto = from(product);
          productDtos.add(productDto);
        }

        return productDtos;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name="id") Long productId) {
        if(productId <= 0) {
            //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            throw new IllegalArgumentException("Products start from Id 0");
        }
       Product product = productService.getProductById(productId);
       if(product == null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       ProductDto productDto = from(product);
       return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto input) {
        Product inputProduct = from(input);
        Product output  = productService.createProduct(inputProduct);
        if(output == null) return null;

        return from(output);
    }

    //ToDo: Add Api Wrapper for Delete

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if(id <= 0) {
            throw new IllegalArgumentException("Wrong Input");
        }

        Product inputProduct = from(productDto);
        Product outputProduct = productService.replaceProduct(id,inputProduct);
        if(outputProduct == null) return null;
        return from(outputProduct);
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
//        product.setCreatedAt(new Date());
//        product.setLastUpdatedAt(new Date());
//        product.setState(State.ACTIVE);
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

}
