package com.jhones.apiclinica.resources;

import com.jhones.apiclinica.models.Agenda;
import com.jhones.apiclinica.models.Doctor;
import com.jhones.apiclinica.models.Patient;
import com.jhones.apiclinica.services.AgendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Clínica médica API Restful - Agenda de horários disponíveis")
@RestController
@RequestMapping("/time")
public class AgendaResource {

    @Autowired
    AgendaService agendaService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Salva um horário")
    @PostMapping
    public Agenda saveTime(@RequestBody @Valid Agenda agenda){
        return agendaService.save(agenda);
    }

    @ApiOperation(value = "Carrega todos os horários disponíveis")
    @GetMapping
    public @ResponseBody ArrayList<Agenda> loadAllAvailableTime(){
        Iterable<Agenda> agendas = agendaService.loadAllAvailableTime();
        //Creates an Arraylist which will receive the Patients and add the links
        ArrayList<Agenda> agendaList = new ArrayList<>();
        //For each iteration, it will extract the link of each Patient and build a link with it.
        for(Agenda agenda: agendas){
            long id = agenda.getId();
            agenda.add(linkTo(methodOn(AgendaResource.class).loadAgendaById(id)).withSelfRel());
            agendaList.add(agenda);
        }
        //We change the return from an Interable to the new ArrayList
        return agendaList;
    }

    @ApiOperation(value = "Carrega um horário disponível pelo id")
    @GetMapping(value = "/{id}")
    public @ResponseBody Agenda loadAgendaById(@PathVariable("id") long id){
        Agenda agenda = agendaService.loadAvailableTime(id);
        agenda.add(linkTo(methodOn(AgendaResource.class).loadAllAvailableTime()).withSelfRel());
        return agenda;
    }
    @ApiOperation(value = "Atualiza um horário")
    @PutMapping
    public Agenda updateTime(@RequestBody @Valid Agenda agenda){
        return agendaService.save(agenda);
    }

    @ApiOperation(value = "Deleta um horário")
    @DeleteMapping
    public Agenda deleteTime(@RequestBody Agenda agenda){
        agendaService.delete(agenda);
        return agenda;
    }

}
