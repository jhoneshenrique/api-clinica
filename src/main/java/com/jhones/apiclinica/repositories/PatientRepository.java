package com.jhones.apiclinica.repositories;

import com.jhones.apiclinica.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findPatientById(long id);
}
