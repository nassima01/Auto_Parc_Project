package com.example.autopark_project.services;

import com.example.autopark_project.entities.Driver;

public interface DriverEligibilityService {
    boolean isDriverEligible(Driver driver, String licenseCategory);
}

