package com.example.autopark_project.repositories;

import com.example.autopark_project.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
