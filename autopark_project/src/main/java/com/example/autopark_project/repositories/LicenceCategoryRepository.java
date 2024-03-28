package com.example.autopark_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.autopark_project.entities.LicenceCategory;

public interface LicenceCategoryRepository extends JpaRepository<LicenceCategory, Long> {
    // You can define custom query methods here if needed
}

