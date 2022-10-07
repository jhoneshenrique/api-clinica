package com.jhones.apiclinica.repositories;

import com.jhones.apiclinica.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findAppointmentById(long id);

    //Checks if there is any appointment booked for that doctor at that time
    @Query(value = "select * from appointment b where b.appointment_date = :appointmentDate and b.appointment_time = :appointmentTime and doctor_id = :doctorId", nativeQuery = true)
    Appointment findBookedAppointment(@Param("appointmentDate") LocalDate appointmentDate, @Param("appointmentTime") LocalTime appointmentTime, @Param("doctorId") Long doctorId);
}
