package com.example.autopark_project.repositories;

import com.example.autopark_project.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}