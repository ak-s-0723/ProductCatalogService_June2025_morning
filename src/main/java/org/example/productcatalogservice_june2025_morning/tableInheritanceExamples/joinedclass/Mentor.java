package org.example.productcatalogservice_june2025_morning.tableInheritanceExamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name="user_id_")
public class Mentor extends User {
    private Long hours;
}
