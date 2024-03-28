package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.services.AvailableVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autopark_project.entities.Vehicle;
import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.repositories.VehicleRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableVehicleServiceImpl implements AvailableVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAvailableVehicles(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        // Filter vehicles based on their trips
        return allVehicles.stream()
                .filter(vehicle -> isAvailable(vehicle, startDate, endDate, startTime, endTime))
                .collect(Collectors.toList());
    }

    private boolean isAvailable(Vehicle vehicle, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        // Check if the vehicle has any trips overlapping with the specified time frame
        return vehicle.getTrips().stream()
                .noneMatch(trip -> isOverlap(startDate, endDate, startTime, endTime, trip));
    }

    private boolean isOverlap(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Trip trip) {
        // Check if the trip overlaps with the specified time frame
        LocalDate tripStartDate = trip.getDepartureDate();
        LocalDate tripEndDate = trip.getArrivalDate();
        LocalTime tripStartTime = trip.getDepartureTime();
        LocalTime tripEndTime = trip.getArrivalTime();

        return !((endDate.isBefore(tripStartDate) || startDate.isAfter(tripEndDate))
                || (endTime.isBefore(tripStartTime) || startTime.isAfter(tripEndTime)));
    }
}
