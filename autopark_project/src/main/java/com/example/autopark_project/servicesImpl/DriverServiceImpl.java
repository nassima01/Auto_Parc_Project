package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.exceptions.DriverNotFoundException;
import com.example.autopark_project.repositories.DriverRepository;
import com.example.autopark_project.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
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

}
