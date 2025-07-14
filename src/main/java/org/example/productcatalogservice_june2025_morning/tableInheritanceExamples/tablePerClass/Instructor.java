package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User {
    private String company;
}
