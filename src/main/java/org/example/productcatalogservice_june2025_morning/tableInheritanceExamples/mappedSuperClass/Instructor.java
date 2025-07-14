package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_instructor")
public class Instructor extends User {
    private String company;
}
