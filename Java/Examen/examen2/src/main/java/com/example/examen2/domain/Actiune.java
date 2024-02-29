package com.example.examen2.domain;

public class Actiune extends Entity{
    String descriere;
    int durata;

    public Actiune(String descriere, int durata) {
        this.descriere = descriere;
        this.durata = durata;
    }

    public Actiune() {
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
