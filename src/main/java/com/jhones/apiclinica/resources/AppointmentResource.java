package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Agenda;
import com.jhones.apiclinica.models.Appointment;
import com.jhones.apiclinica.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentResource {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid Appointment appointment){
        List<Agenda> doctorsAgenda = appointment.getDoctor().getAgenda();
        boolean disponivel = false;
        Iterator<Agenda> iterator = doctorsAgenda.iterator();
        while(iterator.hasNext()){
            String day = iterator.next().getDayOfTheWeek();
            String dayOfWeek = String.valueOf(appointment.getAppointmentDate().getDayOfWeek());
            System.out.println(dayOfWeek);
            if(day.equalsIgnoreCase(dayOfWeek)){
                disponivel = true;
            }
        }
        if (!disponivel==true){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Doctor is not available!");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));
        }
    }
}
