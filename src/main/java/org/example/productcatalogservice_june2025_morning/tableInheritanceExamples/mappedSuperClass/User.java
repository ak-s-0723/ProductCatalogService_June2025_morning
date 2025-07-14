package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.mappedSuperClass;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class User {
    @Id
    private UUID id;

    private String email;

}
