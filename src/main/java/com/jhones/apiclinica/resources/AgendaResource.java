package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Agenda;
import com.jhones.apiclinica.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/time")
public class AgendaResource {

    @Autowired
    AgendaService agendaService;

    @PostMapping
    public Agenda saveTime(@RequestBody @Valid Agenda agenda){
        return agendaService.save(agenda);
    }

    @GetMapping
    public @ResponseBody Iterable<Agenda> loadAllAvailableTime(){
        return agendaService.loadAllAvailableTime();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Agenda loadAgendaById(@PathVariable("id") long id){
        return agendaService.loadAvailableTime(id);
    }
    @PutMapping
    public Agenda updateTime(@RequestBody @Valid Agenda agenda){
        return agendaService.save(agenda);
    }

    @DeleteMapping
    public Agenda deleteTime(@RequestBody Agenda agenda){
        agendaService.delete(agenda);
        return agenda;
    }

}
