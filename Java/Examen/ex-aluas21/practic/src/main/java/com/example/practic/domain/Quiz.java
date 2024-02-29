package com.example.practic.domain;

public class Quiz extends Entity{
    String text;
    String raspunsA;
    String raspunsB;
    String raspunsC;
    String raspunsCorect;

    int punctaj;

    public Quiz(int id){
        super(id);
    }

    public Quiz(int id, String text, String raspunsA, String raspunsB, String raspunsC, String raspunsCorect, int punctaj){
        super(id);
        this.text = text;
        this.raspunsA = raspunsA;
        this.raspunsB = raspunsB;
        this.raspunsC = raspunsC;
        this.raspunsCorect = raspunsCorect;
        this.punctaj = punctaj;
    }

    public String getText(){
        return text;
    }

    public int getPunctaj(){
        return punctaj;
    }

    public void setPunctaj(int punctaj){
        this.punctaj = punctaj;
    }
    public String getRaspunsA(){
        return raspunsA;
    }

    public String getRaspunsB(){
        return raspunsB;
    }

    public String getRaspunsC(){
        return raspunsC;
    }

    public String getRaspunsCorect(){
        return raspunsCorect;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setRaspunsA(String raspunsA){
        this.raspunsA = raspunsA;
    }

    public void setRaspunsB(String raspunsB){
        this.raspunsB = raspunsB;
    }

    public void setRaspunsC(String raspunsC){
        this.raspunsC = raspunsC;
    }

    public void setRaspunsCorect(String raspunsCorect){
        this.raspunsCorect = raspunsCorect;
    }

    @Override
    public String toString(){
        return "Quiz{" +
                "text='" + text + '\'' +
                ", raspunsA='" + raspunsA + '\'' +
                ", raspunsB='" + raspunsB + '\'' +
                ", raspunsC='" + raspunsC + '\'' +
                ", raspunsCorect='" + raspunsCorect + '\'' +
                ", id=" + this.getId() +
                '}';
    }
}
