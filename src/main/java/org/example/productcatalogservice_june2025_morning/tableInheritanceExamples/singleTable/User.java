package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.singleTable;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name="st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    private UUID id;

    private String email;

}
