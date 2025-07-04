package org.example.productcatalogservice_june2025_morning.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Product extends BaseModel {
    private String name;

    private String description;

    private Category category;

    private Double price;

    private String imageUrl;

    //Business Specific field which we don't want to expose to Seller or Buyer
    private Boolean isPrimeSpecific;

    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
