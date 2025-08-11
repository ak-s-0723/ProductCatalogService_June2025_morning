package org.example.productcatalogservice_june2025_morning.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchRequestDto {
    private String query;
    private Integer pageNumber;
    private Integer pageSize;
}
