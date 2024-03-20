package com.example.autopark_project.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.repositories.TripRepository;
import com.example.autopark_project.services.TripService;
import com.example.autopark_project.exceptions.InvalidDateOrderException;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

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

        if (trip.getDepartureDate().isBefore(trip.getArrivalDate()) ||
                (trip.getDepartureDate().isEqual(trip.getArrivalDate()) &&
                        trip.getDepartureTime().isBefore(trip.getArrivalTime()))) {
            return tripRepository.save(trip);
        }
        else {
            throw new InvalidDateOrderException("Departure date must be before arrival date");
        }
    }
}
