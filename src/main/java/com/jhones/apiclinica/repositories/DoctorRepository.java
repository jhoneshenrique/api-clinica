package com.jhones.apiclinica.repositories;

import com.jhones.apiclinica.models.Doctor;
import com.jhones.apiclinica.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findDoctorById(long id);
}
