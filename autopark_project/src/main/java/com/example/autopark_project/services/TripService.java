package com.example.autopark_project.services;

import com.example.autopark_project.entities.Driver;
import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.exceptions.InvalidDateOrderException;

import java.util.List;

public interface TripService {
    List<Trip> getAllTrips();
    Trip getTripById(Long tripId);
    Trip saveTrip(Trip trip) throws InvalidDateOrderException;
}