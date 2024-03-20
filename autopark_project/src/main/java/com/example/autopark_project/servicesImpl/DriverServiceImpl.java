package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.DriverUnavailability;
import com.example.autopark_project.exceptions.DriverNotFoundException;
import com.example.autopark_project.repositories.DriverRepository;
import com.example.autopark_project.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    private final String SERVICE_NAME = "DriverServiceImpl"; // Define service name

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(Long id) throws DriverNotFoundException {
        Optional<Driver> optionalDriver = driverRepository.findById(String.valueOf(id));
        if (optionalDriver.isPresent()) {
            return optionalDriver.get();
        } else {
            throw new DriverNotFoundException("Driver not found with id: " + id, SERVICE_NAME); // Pass service name
        }
    }

    @Override
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(Long id) throws DriverNotFoundException {
        if (driverRepository.existsById(String.valueOf(id))) {
            driverRepository.deleteById(String.valueOf(id));
        } else {
            throw new DriverNotFoundException("Driver not found with id: " + id, SERVICE_NAME); // Pass service name
        }
    }

    @Override
    public Driver updateDriver(Long id, Driver updatedDriver) throws DriverNotFoundException {
        Optional<Driver> optionalDriver = driverRepository.findById(String.valueOf(id));
        if (optionalDriver.isPresent()) {
            Driver existingDriver = optionalDriver.get();
            // Update driver properties
            existingDriver.setFirstName(updatedDriver.getFirstName());
            existingDriver.setLastName(updatedDriver.getLastName());
            existingDriver.setDateOfBirth(updatedDriver.getDateOfBirth());
            // Save the updated driver
            return driverRepository.save(existingDriver);
        } else {
            throw new DriverNotFoundException("Driver not found with id: " + id, SERVICE_NAME); // Pass service name
        }
    }

    @Override
    public List<Driver> getAvailableDriversInPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Driver> allDrivers = driverRepository.findAll();
        List<Driver> availableDrivers = new ArrayList<>();

        for (Driver driver : allDrivers) {
            if (isDriverAvailableInPeriod(driver, startDateTime, endDateTime)) {
                availableDrivers.add(driver);
            }
        }

        return availableDrivers;
    }
    private boolean isDriverAvailableInPeriod(Driver driver, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        for (DriverUnavailability availability : driver.getUnavailabilities()) {
            LocalDateTime driverStartDateTime = availability.getStart();
            LocalDateTime driverEndDateTime = availability.getEnd();

            if (!(startDateTime.isAfter(driverEndDateTime) || endDateTime.isBefore(driverStartDateTime))) {
                return false;
            }
        }
        return true;
    }
}
