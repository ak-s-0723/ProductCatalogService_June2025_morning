package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class Ta extends User {
    private Double ratings;
}
