package com.example.demo.domain.dto;

public class ProgramariLuna {
    private int luna;
    private int nrProg;

    public ProgramariLuna(int luna, int nrProg){
        this.luna = luna;
        this.nrProg = nrProg;
    }

    public int getLuna(){
        return luna;
    }

    public int getNrProg(){
        return nrProg;
    }

    public void setLuna(int luna){
        this.luna = luna;
    }

    public void setNrProg(int nrProg){
        this.nrProg = nrProg;
    }

}
