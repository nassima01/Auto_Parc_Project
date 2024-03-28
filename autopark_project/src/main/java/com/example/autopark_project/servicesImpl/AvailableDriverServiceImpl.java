package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.services.AvailableDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.repositories.DriverRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableDriverServiceImpl implements AvailableDriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAvailableDrivers(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        List<Driver> allDrivers = driverRepository.findAll();
        // Filter drivers based on their trips
        return allDrivers.stream()
                .filter(driver -> isAvailable(driver, startDate, endDate, startTime, endTime))
                .collect(Collectors.toList());
    }

    private boolean isAvailable(Driver driver, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        // Check if the driver has any trips overlapping with the specified time frame
        return driver.getTrips().stream()
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
