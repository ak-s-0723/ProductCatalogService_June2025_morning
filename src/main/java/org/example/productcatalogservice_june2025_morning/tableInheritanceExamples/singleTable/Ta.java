package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value = "1")
public class Ta extends User {
    private Double ratings;
}
