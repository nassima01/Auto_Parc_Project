package com.example.autopark_project.services;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.exceptions.DriverNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver getDriverById(Long id) throws DriverNotFoundException;

    Driver addDriver(Driver driver);

    void deleteDriver(Long id) throws DriverNotFoundException;

    Driver updateDriver(Long id, Driver updatedDriver) throws DriverNotFoundException;

    List<Driver> getAvailableDriversInPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
