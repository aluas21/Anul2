package com.example.examenp3.domain;

import java.time.LocalDate;

public class Activitate extends Entity {
    LocalDate data;
    int nrPasi;
    String descriere;
    int durata;

    public Activitate(int id, LocalDate data, int nrPasi, String descriere, int durata) {
        super(id);
        this.data = data;
        this.nrPasi = nrPasi;
        this.descriere = descriere;
        this.durata = durata;
    }

    //get

    public LocalDate getData() {
        return data;
    }

    public int getNrPasi() {
        return nrPasi;
    }

    public String getDescriere() {
        return descriere;
    }

    public int getDurata() {
        return durata;
    }

    //set

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNrPasi(int nrPasi) {
        this.nrPasi = nrPasi;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString(){
        return
                "data: " + data.toString() +
                ", nrPasi= " + nrPasi + '\'' +
                ", descriere= " + descriere +
                ", durata= " + durata +
                ", id=" + this.getId() +
                '}';

    }




}
