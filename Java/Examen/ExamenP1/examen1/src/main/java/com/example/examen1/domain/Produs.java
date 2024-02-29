package com.example.examen1.domain;

import java.io.Serializable;

public class Produs extends Entity implements Serializable {
    String marca;
    String nume;
    int pret;
    int cantitate;

    public Produs(int id, String marca, String nume, int pret, int cantitate) {
        super(id);
        this.marca = marca;
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Produs() {
    }

    public String getMarca() {
        return marca;
    }

    public String getNume() {
        return nume;
    }

    public int getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

}
