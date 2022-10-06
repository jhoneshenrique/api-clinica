package com.jhones.apiclinica.services;

import com.jhones.apiclinica.models.Appointment;
import com.jhones.apiclinica.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment save(Appointment appointment){
        return  appointmentRepository.save(appointment);
    }

    public Iterable<Appointment> loadAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment loadAppointmentById(long id){
        return appointmentRepository.findAppointmentById(id);
    }

    public Appointment update(Appointment appointment){
        return  appointmentRepository.save(appointment);
    }

    public Appointment delete(Appointment appointment){
        appointmentRepository.delete(appointment);
        return  appointment;
    }
}
