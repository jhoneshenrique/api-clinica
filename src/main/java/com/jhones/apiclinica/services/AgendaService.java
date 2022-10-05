package com.jhones.apiclinica.services;

import com.jhones.apiclinica.models.Agenda;
import com.jhones.apiclinica.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

    @Autowired
    AgendaRepository agendaRepository;

    public Agenda save(Agenda agenda){
        return agendaRepository.save(agenda);
    }

    public Iterable<Agenda> loadAllAvailableTime(){
        Iterable<Agenda> availableTime = agendaRepository.findAll();
        return  availableTime;
    }

    public void delete(Agenda agenda){
        agendaRepository.delete(agenda);
    }

    public Agenda update(Agenda agenda){
        return agendaRepository.save(agenda);
    }
}
