package com.hemendra.patient_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name cannot exceed the 100 char")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    private String date_of_birth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Date of Birth is required")
    private String registered_date;
}
