package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    private Double ratings;
}
