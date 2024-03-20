package com.example.autopark_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {
    private Long cin;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Set<Long> driverLicenses;
    private Set<Long> tripIds;
}
