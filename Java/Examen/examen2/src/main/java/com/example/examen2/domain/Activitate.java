package com.example.examen2.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Activitate extends Entity {
    LocalDate data;
    int NrPasi;
    int NrOreSomn;
    ArrayList<Actiune> actiuni;

    public Activitate(int id, LocalDate data, int NrPasi, int NrOreSomn, ArrayList<Actiune> actiuni) {
        super(id);
        this.data = data;
        this.NrPasi = NrPasi;
        this.NrOreSomn = NrOreSomn;
        this.actiuni = actiuni;
    }

    public Activitate() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNrPasi() {
        return NrPasi;
    }

    public void setNrPasi(int NrPasi) {
        this.NrPasi = NrPasi;
    }

    public int getNrOreSomn() {
        return NrOreSomn;
    }

    public void setNrOreSomn(int NrOreSomn) {
        this.NrOreSomn = NrOreSomn;
    }

    public ArrayList<Actiune> getActiuni() {
        return actiuni;
    }

    public void setActiuni(ArrayList<Actiune> actiuni) {
        this.actiuni = actiuni;
    }

    public String ToString(){
        String s = this.getData() + ";" + this.getNrPasi() + ";" + this.getNrOreSomn();
        for (Actiune actiune : this.getActiuni()) {
            s += ";" + actiune.getDescriere() + "," + actiune.getDurata();
        }
        return s;
    }

}
