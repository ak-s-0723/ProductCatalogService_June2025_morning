package org.example.productcatalogservice_june2025_morning.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Category extends BaseModel {
    private String name;

    private String description;

    private List<Product> products;

    public Category() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
        this.products = new ArrayList<>();
    }
}
