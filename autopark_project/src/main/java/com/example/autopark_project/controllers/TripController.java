package com.example.autopark_project.controllers;

import com.example.autopark_project.entities.Trip;
import com.example.autopark_project.exceptions.InvalidDateOrderException;
import com.example.autopark_project.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable("id") Long id) {
        Trip trip = tripService.getTripById(id);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        try {
            Trip savedTrip = tripService.saveTrip(trip);
            return new ResponseEntity<>(savedTrip, HttpStatus.CREATED);
        } catch (InvalidDateOrderException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
