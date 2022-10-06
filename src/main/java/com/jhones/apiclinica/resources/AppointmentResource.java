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
import java.time.LocalDate;
import java.time.LocalTime;
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
        boolean dayAvailable = false;
        //Checks if the doctor can attent the appointment that day of the week
        Iterator<Agenda> iterator = doctorsAgenda.iterator();
        while(iterator.hasNext()){
            String day = iterator.next().getDayOfTheWeek();
            if(day.equalsIgnoreCase(String.valueOf(appointment.getAppointmentDate().getDayOfWeek()))){
                dayAvailable = true;
            }
        }

        //Checks if the doctor can attent the appointment at that time
        boolean timeAvailable = false;
        Iterator<Agenda> iteratorTime = doctorsAgenda.iterator();
        while(iteratorTime.hasNext()){
            LocalTime time = iteratorTime.next().getBeginsAt();
            if(time.compareTo(appointment.getAppointmentTime())==0){
                timeAvailable = true;
            }
        }
        

        //Alert the user if the doctor's agenda or the appointment time is not available
        if (!dayAvailable || !timeAvailable){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Doctor is not available!");
        }
//        if(!appointmentAvailable){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This time is not available!");
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));

    }
}
