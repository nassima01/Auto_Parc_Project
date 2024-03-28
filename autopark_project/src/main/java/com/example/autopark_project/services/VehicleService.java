package com.example.autopark_project.services;

import com.example.autopark_project.entities.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int id);
    Vehicle addVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
    Vehicle updateVehicle(int id, Vehicle vehicle);
}

