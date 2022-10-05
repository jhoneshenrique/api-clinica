package com.jhones.apiclinica.services;

import com.jhones.apiclinica.models.Doctor;
import com.jhones.apiclinica.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Iterable<Doctor> loadAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor loadDoctorById(long id){
        return doctorRepository.findDoctorById(id);
    }

    public Doctor update(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor delete (Doctor doctor){
        doctorRepository.delete(doctor);
        return doctor;
    }
}
