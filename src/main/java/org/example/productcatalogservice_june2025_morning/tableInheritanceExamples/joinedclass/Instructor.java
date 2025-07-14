package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_instructor")
@PrimaryKeyJoinColumn(name="user_id_")
public class Instructor extends User {
    private String company;
}
