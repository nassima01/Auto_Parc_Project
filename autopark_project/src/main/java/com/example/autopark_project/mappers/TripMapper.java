package com.example.autopark_project.mappers;

import com.example.autopark_project.dto.TripDTO;
import com.example.autopark_project.entities.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

    public TripDTO tripToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setDepartureDate(trip.getDepartureDate());
        tripDTO.setDepartureTime(trip.getDepartureTime());
        tripDTO.setArrivalDate(trip.getArrivalDate());
        tripDTO.setArrivalTime(trip.getArrivalTime());
        return tripDTO;
    }

    public Trip dtoToTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setId(tripDTO.getId());
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setDepartureTime(tripDTO.getDepartureTime());
        trip.setArrivalDate(tripDTO.getArrivalDate());
        trip.setArrivalTime(tripDTO.getArrivalTime());
        return trip;
    }
}
