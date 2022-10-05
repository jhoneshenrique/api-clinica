package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Doctor;
import com.jhones.apiclinica.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorResource {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    public Doctor save(@RequestBody @Valid Doctor doctor){
        return  doctorService.save(doctor);
    }

    @GetMapping
    public @ResponseBody Iterable<Doctor> loadAllDoctors(){
        return doctorService.loadAllDoctors();
    }

    @GetMapping
    @RequestMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody Doctor loadDoctorById(@PathVariable("id") long id){
        return doctorService.loadDoctorById(id);
    }
}
