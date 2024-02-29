package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Programare extends Entity implements Serializable {
    private Pacient pacient;
    private int pacientId;  //se foloseste doar la scrierea in tabele
    private String motiv;
    private LocalDate date;
    private LocalTime time;

    public Programare() {
    }

    @Override
    public String toString() {
        return "Programare{" +
                "pacient=" + pacient +
                ", motiv='" + motiv + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", id=" + id +
                '}';
    }

    public Programare(int id, Pacient pacient, LocalDate date, LocalTime time, String motiv){
        super(id);
        this.id = id;
        this.pacient = pacient;
        this.date = date;
        this.time = time;
        this.motiv = motiv;
    }

    public void setMotiv(String motiv) {
        this.motiv = motiv;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMotiv() {
        return motiv;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public int getPacientId() {
        return pacient.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programare that = (Programare) o;
        return Objects.equals(pacient, that.pacient) && Objects.equals(motiv, that.motiv) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pacient, motiv, date, time);
    }
}
