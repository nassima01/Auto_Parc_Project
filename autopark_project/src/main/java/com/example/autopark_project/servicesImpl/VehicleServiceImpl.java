package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.entities.Vehicle;
import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.exceptions.VehicleNotFoundException;
import com.example.autopark_project.repositories.VehicleRepository;
import com.example.autopark_project.services.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(int id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(String.valueOf(id));
        return optionalVehicle.orElse(null);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Vehicle updateVehicle(int id, Vehicle updatedVehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(String.valueOf(id));

        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            existingVehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
            existingVehicle.setBrandModel(updatedVehicle.getBrandModel());
            existingVehicle.setRequiredLicenseType(updatedVehicle.getRequiredLicenseType());
            existingVehicle.setGrisCard(updatedVehicle.getGrisCard());
            existingVehicle.setMileage(updatedVehicle.getMileage());
            existingVehicle.setAvailability(updatedVehicle.getAvailability());
            existingVehicle.setRequiredLicenseType(updatedVehicle.getRequiredLicenseType());
            try {
                return vehicleRepository.save(existingVehicle);
            } catch (Exception e) {
                // Handle exception, log error, or throw custom exception
                throw new RuntimeException("Failed to update vehicle with ID: " + id, e);
            }
        } else {
            // Vehicle not found, throw custom exception or return null
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found");
        }
    }

}
