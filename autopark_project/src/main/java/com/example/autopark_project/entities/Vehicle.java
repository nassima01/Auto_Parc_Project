package com.example.autopark_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "brand_model")
    private String brandModel;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private LicenceCategoryType requiredLicenseType;

    @OneToOne
    @JoinColumn(name = "gris_card_id")
    private GrisCard grisCard;

    @Column(name = "mileage")
    private float mileage;

    @OneToOne
    @JoinColumn(name = "vignette_id")
    private Vignette vignette;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private AvailabilityStatus availability;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<TechnicalVisit> technicalVisits;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Set<Trip> trips = new HashSet<>();
}
