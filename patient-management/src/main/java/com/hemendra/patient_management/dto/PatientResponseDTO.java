package com.hemendra.patient_management.dto;

import lombok.Data;

@Data
public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String address;
    private String date_of_birth;
}
