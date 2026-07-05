package com.hemendra.patient_management.service;

import com.hemendra.patient_management.model.Patient;
import com.hemendra.patient_management.dto.PatientRequestDTO;

import java.time.LocalDate;

public class MappingDTOtoPatient {

    public static Patient toPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDate_of_birth(LocalDate.parse(patientRequestDTO.getDate_of_birth()));
        patient.setRegistered_date(LocalDate.parse(patientRequestDTO.getRegistered_date()));

        return patient;
    }
}
