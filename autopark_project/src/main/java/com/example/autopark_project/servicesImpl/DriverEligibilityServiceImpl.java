package com.example.autopark_project.servicesImpl;

import com.example.autopark_project.entities.LicenceCategory;
import com.example.autopark_project.services.DriverEligibilityService;
import org.springframework.stereotype.Service;
import com.example.autopark_project.entities.Driver;

@Service
public class DriverEligibilityServiceImpl implements DriverEligibilityService {

    private final LicenceCategoryRepository licenceCategoryRepository;

    public DriverEligibilityServiceImpl(LicenceCategoryRepository licenceCategoryRepository) {
        this.licenceCategoryRepository = licenceCategoryRepository;
    }

    @Override
    public boolean isDriverEligible(Driver driver, String licenseCategory) {
        // Retrieve the driver's license categories
        Set<LicenceCategory> driverLicenses = driver.getDriverLicenses();

        // Check if the driver has the specified license category
        return driverLicenses.stream()
                .anyMatch(license -> license.getCategoryType().name().equals(licenseCategory));
    }
}