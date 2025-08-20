package org.example.productcatalogservice_june2025_morning.services;

import org.example.productcatalogservice_june2025_morning.clients.FakeStoreApiClient;
import org.example.productcatalogservice_june2025_morning.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_june2025_morning.models.Category;
import org.example.productcatalogservice_june2025_morning.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fkps")
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    //H/W for 4/7/2025
    @Override
    public List<Product> getAllProductDetails() {
        return null;
    }

// Before Service Layer Implementation
//    @Override
//    public Product getProductById(Long productId) {
//       RestTemplate restTemplate = restTemplateBuilder.build();
////        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/{productId}",
////                FakeStoreProductDto.class,
////                productId);
//
//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
//                FakeStoreProductDto.class,
//                productId);
//
//        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
//                fakeStoreProductDtoResponseEntity.hasBody()) {
//            return from(fakeStoreProductDtoResponseEntity.getBody());
//        }
//
//       return null;
//    }

    @Override
    public Product getProductById(Long productId) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
       if(fakeStoreProductDto == null) return  null;
       return from(fakeStoreProductDto);
    }

    //ToDo : Refactor in Client Layer and use requestForEntity
    @Override
    public Product createProduct(Product input) {
       RestTemplate restTemplate = restTemplateBuilder.build();
       FakeStoreProductDto inputFakeStoreProductDto = from(input);
       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
               restTemplate.postForEntity("https://fakestoreapi.com/products",
               inputFakeStoreProductDto,
               FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                fakeStoreProductDtoResponseEntity.hasBody()) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    //ToDo : Refactor in Client Layer
    @Override
    public Product replaceProduct(Long productId, Product input) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto inputFakeStoreProductDto = from(input);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{productId}",
                        inputFakeStoreProductDto, FakeStoreProductDto.class,productId);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                fakeStoreProductDtoResponseEntity.hasBody()) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public Product getProductBasedOnUserScope(Long productId, Long userId) {
        return null;
    }


//    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
//    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
