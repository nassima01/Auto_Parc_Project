package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.Vehicle;
import com.example.autopark_project.services.DriverService;
import com.example.autopark_project.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.repositories.TripRepository;
import com.example.autopark_project.services.TripService;
import com.example.autopark_project.exceptions.InvalidDateOrderException;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    private VehicleService vehicleService;
    private DriverService driverService;

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getTripById(Long tripId) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        return tripOptional.orElse(null);
    }

    @Override
    public Trip saveTrip(Trip trip) throws InvalidDateOrderException {
        // Check if departure date is before arrival date
        if (trip.getDepartureDate().isAfter(trip.getArrivalDate()) ||
                (trip.getDepartureDate().isEqual(trip.getArrivalDate()) &&
                        trip.getDepartureTime().isAfter(trip.getArrivalTime()))) {
            throw new InvalidDateOrderException("Departure date must be before arrival date");
        }

        assignDriverAndVehicle(trip);
        return tripRepository.save(trip);
    }

    private void assignDriverAndVehicle(Trip trip) {
        LocalDateTime startDateTime = LocalDateTime.of(trip.getDepartureDate(), trip.getDepartureTime());
        LocalDateTime endDateTime = LocalDateTime.of(trip.getArrivalDate(), trip.getArrivalTime());
    }

}
