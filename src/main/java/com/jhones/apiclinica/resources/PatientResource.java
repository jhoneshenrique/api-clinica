package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientResource {

    @Autowired
    PatientService patientService;

    //List all the patients
    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<Patient> listAllPatients(){
        Iterable<Patient> patients = patientService.loadAllPatients();
        return patients;
    }

    //List a specific patient by its id
    @GetMapping(value ="/{id}" ,produces = "application/json")
    public @ResponseBody Patient listPatient(@PathVariable("id") long id){
        Patient patient = patientService.findPatientById(id);
        return patient;
    }

    //Save a patient
    @PostMapping
    public Patient savePatient(@RequestBody @Valid Patient patient){
        return patientService.save(patient);
    }

    //Update the data from the patient
    @PutMapping
    public Patient updatePatient(@RequestBody @Valid Patient patient){
        return patientService.update(patient);
    }

    //Delete a patient
    @DeleteMapping
    public Patient deletePatient(@RequestBody Patient patient){
        patientService.delete(patient);
        return patient;
    }
}
