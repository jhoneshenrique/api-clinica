package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Doctor;
import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.services.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Api(value = "Clínica médica API Restful - Médicos")
@RestController
@RequestMapping("/doctor")
public class DoctorResource {
    @Autowired
    DoctorService doctorService;


    @ApiOperation(value = "Cadastra um médico no sistema")
    @PostMapping
    public Doctor save(@RequestBody @Valid Doctor doctor){
        return  doctorService.save(doctor);
    }


    @ApiOperation(value = "Carrega todos os médicos no sistema")
    @GetMapping(produces = "application/json")
    public @ResponseBody ArrayList<Doctor> loadAllDoctors(){
        Iterable<Doctor> doctors = doctorService.loadAllDoctors();
        //Creates an Arraylist which will receive the Patients and add the links
        ArrayList<Doctor> doctorList = new ArrayList<>();
        //For each iteration, it will extract the link of each Patient and build a link with it.
        for(Doctor doctor: doctors){
            long id = doctor.getId();
            doctor.add(linkTo(methodOn(DoctorResource.class).loadDoctorById(id)).withSelfRel());
            doctorList.add(doctor);
        }
        //We change the return from an Interable to the new ArrayList
        return doctorList;
    }


    @ApiOperation(value = "Carrega um médico pelo id")
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody Doctor loadDoctorById(@PathVariable("id") long id){
        Doctor doctor = doctorService.loadDoctorById(id);
        doctor.add(linkTo(methodOn(DoctorResource.class).loadAllDoctors()).withSelfRel());
        return doctor;
    }

    @ApiOperation(value = "Atualiza os dados de um médico")
    @PutMapping
    public Doctor update(@RequestBody @Valid Doctor doctor){
        return doctorService.save(doctor);
    }


    @ApiOperation(value = "Deleta um médico")
    @DeleteMapping
    public Doctor delete(@RequestBody Doctor doctor){
        doctorService.delete(doctor);
        return doctor;
    }

}
