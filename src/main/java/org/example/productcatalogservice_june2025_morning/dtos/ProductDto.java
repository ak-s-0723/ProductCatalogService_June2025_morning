package org.example.productcatalogservice_june2025_morning.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_june2025_morning.models.Category;

@Setter
@Getter
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private CategoryDto category;

    private Double price;

    private String imageUrl;
}
