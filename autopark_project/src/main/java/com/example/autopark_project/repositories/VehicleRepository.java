package com.example.autopark_project.repositories;

import com.example.autopark_project.entities.AvailabilityStatus;
import com.example.autopark_project.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByAvailability(AvailabilityStatus availability);
}