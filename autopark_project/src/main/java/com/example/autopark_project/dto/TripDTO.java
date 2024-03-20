package com.example.autopark_project.dto;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.Vehicle;
import com.example.autopark_project.entities.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDTO {
    private int id;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String departure;
    private String destination;
    private VehicleType vehicleType;
    private int numberOfPassenger;
    private String otherDetails;
    private String status;
    private Long driverId;
    private Long vehicleId;
}
