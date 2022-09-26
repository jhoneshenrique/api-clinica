package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.services.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Clínica médica API Restful - Pacientes")
@RestController
@RequestMapping("/patient")
public class PatientResource {

    @Autowired
    PatientService patientService;

    //List all the patients
    @ApiOperation(value = "Retorna uma lista de pacientes")
    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<Patient> listAllPatients(){
        Iterable<Patient> patients = patientService.loadAllPatients();
        return patients;
    }

    //List a specific patient by its id
    @ApiOperation(value = "Retorna um paciente específico pelo código")
    @GetMapping(value ="/{id}" ,produces = "application/json")
    public @ResponseBody Patient listPatient(@PathVariable("id") long id){
        Patient patient = patientService.findPatientById(id);
        return patient;
    }

    //Save a patient
    @ApiOperation(value = "Cadastra um paciente")
    @PostMapping
    public Patient savePatient(@RequestBody @Valid Patient patient){
        return patientService.save(patient);
    }

    //Update the data from the patient
    @ApiOperation(value = "Atualiza os dados de um paciente")
    @PutMapping
    public Patient updatePatient(@RequestBody @Valid Patient patient){
        return patientService.update(patient);
    }

    //Delete a patient
    @ApiOperation(value = "Delete um paciente")
    @DeleteMapping
    public Patient deletePatient(@RequestBody Patient patient){
        patientService.delete(patient);
        return patient;
    }
}
