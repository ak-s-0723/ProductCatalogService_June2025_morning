package org.example.productcatalogservice_june2025_morning.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;

    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;
}
