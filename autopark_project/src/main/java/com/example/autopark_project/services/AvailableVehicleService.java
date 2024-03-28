package com.example.autopark_project.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.example.autopark_project.entities.Vehicle;

public interface AvailableVehicleService {
    List<Vehicle> getAvailableVehicles(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
}

