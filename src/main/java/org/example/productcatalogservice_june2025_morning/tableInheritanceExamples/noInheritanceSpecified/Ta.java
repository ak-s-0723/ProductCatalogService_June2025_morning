package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.noInheritanceSpecified;

import jakarta.persistence.Entity;

@Entity(name="ni_ta")
public class Ta extends User {
    private Double ratings;
}
