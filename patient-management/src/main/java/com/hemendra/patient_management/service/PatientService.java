package com.hemendra.patient_management.service;

import com.hemendra.patient_management.exception.EmailAlreadyExistsException;
import com.hemendra.patient_management.exception.PatientNotFoundException;
import com.hemendra.patient_management.grpc.BillingServiceGrpcClient;
import com.hemendra.patient_management.kafka.KafkaProducer;
import com.hemendra.patient_management.model.Patient;
import com.hemendra.patient_management.dto.PatientRequestDTO;
import com.hemendra.patient_management.dto.PatientResponseDTO;
import com.hemendra.patient_management.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private BillingServiceGrpcClient billingServiceGrpcClient;

    @Autowired
    private KafkaProducer kafkaProducer;

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepo.findAll();

        return patients.stream().map(patient -> MappingPatientToDTO.mapping(patient)).toList();

    }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO){

        if(patientRepo.existsByEmail(patientRequestDTO.getEmail()))
        {
            throw new EmailAlreadyExistsException("A patient with this email already exist : " + patientRequestDTO.getEmail());
        }

         Patient patient = MappingDTOtoPatient.toPatient(patientRequestDTO);
         Patient newPatient = patientRepo.save(patient);

         billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getEmail(), patient.getAddress());

        kafkaProducer.sendEvent(newPatient);

         //Optional<Patient> patient1 = patientRepo.findById(patient.getId());
         return MappingPatientToDTO.mapping(newPatient);
    }

    public ResponseEntity<PatientResponseDTO> updatePatient(UUID id, PatientRequestDTO patientRequestDTO){

        Patient patient = patientRepo.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not exist with this Id"));

        if(patientRepo.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id))
        {
            throw new EmailAlreadyExistsException("A patient with this email already exist : " + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDate_of_birth(LocalDate.parse(patientRequestDTO.getDate_of_birth()));
//        patient.setRegistered_date(patient1.getRegistered_date());

        PatientResponseDTO patientResponseDTO = MappingPatientToDTO.mapping(patient);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.CREATED);

    }

    public void deletePatient(UUID id){
        patientRepo.deleteById(id);
    }
}
