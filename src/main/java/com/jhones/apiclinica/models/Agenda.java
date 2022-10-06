package com.jhones.apiclinica.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Agenda extends RepresentationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String dayOfTheWeek;
    @NotNull
    private LocalTime beginsAt;
    @NotNull
    private LocalTime endsAt;


    public Agenda(){}

    public Agenda(long id, String dayOfTheWeek, LocalTime beginsAt, LocalTime endsAt) {
        System.out.println("Dia "+dayOfTheWeek);
        System.out.println("Inicio "+beginsAt);
        this.id = id;
        this.dayOfTheWeek = dayOfTheWeek;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public LocalTime getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(LocalTime beginsAt) {
        this.beginsAt = beginsAt;
    }

    public LocalTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalTime endsAt) {
        this.endsAt = endsAt;
    }
}
