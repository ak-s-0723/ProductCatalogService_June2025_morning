package org.example.productcatalogservice_june2025_morning.controllers;

import org.example.productcatalogservice_june2025_morning.dtos.ProductDto;
import org.example.productcatalogservice_june2025_morning.models.Product;
import org.example.productcatalogservice_june2025_morning.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;


    @Test
    public void TestGetProductById_OnValidId_RunSuccessfully() {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone 15");
        product.setPrice(150000D);
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(id);

        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(id,productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone 15",productDtoResponseEntity.getBody().getName());
        assertEquals(HttpStatus.OK,productDtoResponseEntity.getStatusCode());
    }


    @Test
    public void TestGetProductById_WithInvalidId_ResultsInIllegalArgumentException() {
        //Arrange
        Long id = -1L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class,()->productController.getProductById(id));
        assertEquals("Invalid productId",exception.getMessage());
    }

    @Test
    public void TestGetProductById_ServiceThrowsException_ResultsInSameException() {
      //Arrange
         Long id = 21L;
        when(productService.getProductById(id)).thenThrow(new RuntimeException());

        //Act and Assert
        assertThrows(RuntimeException.class,()->productController.getProductById(id));
    }

    @Test
    public void TestCreateProductWithValidInput_RunSuccessfully() {
        //Arrange
        ProductDto input = new ProductDto();
        input.setId(10L);
        input.setName("MacBook Air");
        input.setPrice(200000D);

        Product product = new Product();
        product.setId(10L);
        product.setName("MacBook Air");
        product.setPrice(200000D);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        //Act
        ProductDto responseProductDto  = productController.createProduct(input);

        //Assert
        assertNotNull(responseProductDto);
        assertEquals(10L,responseProductDto.getId());
        assertEquals("MacBook Air",responseProductDto.getName());
        assertEquals(200000D,responseProductDto.getPrice());
    }

}