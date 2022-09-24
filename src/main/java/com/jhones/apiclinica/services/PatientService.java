package com.jhones.apiclinica.services;

import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    //Save a patient
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    //Select all patients
    public Iterable<Patient> loadAllPatients(){
       Iterable<Patient> patients = patientRepository.findAll();
       return patients;
    }

    //Return a specific patient
    public Patient findPatientById(long id){
        Patient patient = patientRepository.findPatientById(id);
        return patient;
    }

    //Delete a patient
    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    //Update
    public Patient update(Patient patient){
        return patientRepository.save(patient);
    }
}
