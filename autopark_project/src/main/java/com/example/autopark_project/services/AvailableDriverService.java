package com.example.autopark_project.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.example.autopark_project.entities.Driver;

public interface AvailableDriverService {
    List<Driver> getAvailableDrivers(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
}

