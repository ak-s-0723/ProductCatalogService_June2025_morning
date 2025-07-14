package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User {
    private Long hours;
}
