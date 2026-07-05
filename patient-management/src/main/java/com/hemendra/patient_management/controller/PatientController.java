package com.hemendra.patient_management.controller;

import com.hemendra.patient_management.dto.CreatePatientValidationGroup;
import com.hemendra.patient_management.dto.PatientRequestDTO;
import com.hemendra.patient_management.dto.PatientResponseDTO;
import com.hemendra.patient_management.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patients")
@Tag(name = "Patient", description = "API document for patient controller")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Get the patient")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        return new ResponseEntity<>(patientService.getPatient(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Post the patient")
    public ResponseEntity<PatientResponseDTO> addpatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.addPatient(patientRequestDTO),HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "update the patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@RequestParam UUID id, @Validated(Builder.Default.class) @RequestBody PatientRequestDTO patientRequestDTO){
        return patientService.updatePatient(id,patientRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
