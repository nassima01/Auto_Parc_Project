package com.example.autopark_project.services;

import com.example.autopark_project.entities.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int id);
    Vehicle addVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
    Vehicle updateVehicle(int id, Vehicle vehicle);
    public List<Vehicle> getAvailableVehicles();
}

