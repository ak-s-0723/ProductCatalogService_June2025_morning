package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private Long hours;
}
