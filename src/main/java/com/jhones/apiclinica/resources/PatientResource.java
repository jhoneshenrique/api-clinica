package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.services.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Clínica médica API Restful - Pacientes")
@RestController
@RequestMapping("/patient")
public class PatientResource {

    @Autowired
    PatientService patientService;

    //List all the patients
    @ApiOperation(value = "Retorna uma lista de pacientes")
    @GetMapping(produces = "application/json")
    public @ResponseBody ArrayList<Patient> listAllPatients(){
        Iterable<Patient> patients = patientService.loadAllPatients();
        //Creates an Arraylist which will receive the Patients and add the links
        ArrayList<Patient> patientsList = new ArrayList<>();
        //For each iteration, it will extract the link of each Patient and build a link with it.
        for(Patient patient : patients){
            long id = patient.getId();
            patient.add(linkTo(methodOn(PatientResource.class).listPatient(id)).withSelfRel());
            patientsList.add(patient);
        }
        //We change the return from an Interable to the new ArrayList
        return patientsList;
    }

    //List a specific patient by its id
    @ApiOperation(value = "Retorna um paciente específico pelo código")
    @GetMapping(value ="/{id}" ,produces = "application/json")
    public @ResponseBody Patient listPatient(@PathVariable("id") long id){
        Patient patient = patientService.findPatientById(id);
        patient.add(linkTo(methodOn(PatientResource.class).listAllPatients()).withSelfRel());
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
