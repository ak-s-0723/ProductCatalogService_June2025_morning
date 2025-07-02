package org.example.productcatalogservice_june2025_morning.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class BaseModel {
    private Long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private State state;
}
