package com.jhones.apiclinica.repositories;

import com.jhones.apiclinica.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Agenda findAgendaById(long id);
}
