package com.hemendra.patient_management.service;

import com.hemendra.patient_management.model.Patient;
import com.hemendra.patient_management.dto.PatientResponseDTO;

public class MappingPatientToDTO {

    public static PatientResponseDTO mapping(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setDate_of_birth(patient.getDate_of_birth().toString());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());

        return patientResponseDTO;

    }
}
