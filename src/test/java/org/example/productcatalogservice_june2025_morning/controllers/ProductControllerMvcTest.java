package org.example.productcatalogservice_june2025_morning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_june2025_morning.dtos.ProductDto;
import org.example.productcatalogservice_june2025_morning.models.Product;
import org.example.productcatalogservice_june2025_morning.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProductsEndpoint_ReturnsOk() throws Exception {
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void TestGetAllProductsEndpoint_RunSucessfully() throws Exception {
        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone");
        product1.setPrice(100000D);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);

        when(productService.getAllProductDetails()).thenReturn(productList);

        //Creating this because we are returning productDto list from Method and Json Structure
        // of both Product and ProductDto is different
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Iphone");
        productDto.setPrice(100000D);
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);

        String expectedResponse = objectMapper.writeValueAsString(productDtos);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

    }

    @Test
    public void TestCreateProductEndpoint_RunSuccessfully() throws Exception {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setName("Macbook");

        Product product = new Product();
        product.setId(10L);
        product.setName("Macbook");

        when(productService.createProduct(any(Product.class))).thenReturn(product);


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }
}
