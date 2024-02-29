package com.example.examen2.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConverActivitate implements EntityConvert<Activitate>{
    @Override
    public Activitate fromString(String line) {
        String[] fields = line.split(";");
        int id = Integer.parseInt(fields[0]);
        LocalDate data = LocalDate.parse(fields[1]);
        int NrPasi = Integer.parseInt(fields[2]);
        int NrOreSomn = Integer.parseInt(fields[3]);
        ArrayList<Actiune> actiuni = new ArrayList<>();
        for (int i = 4; i < fields.length; i++) {
            String[] fields2 = fields[i].split(",");
            String descriere = fields2[0];
            int durata = Integer.parseInt(fields2[1]);
            actiuni.add(new Actiune(descriere, durata));
        }
        return new Activitate(id, data, NrPasi, NrOreSomn, actiuni);
    }

    @Override
    public String ToString(Activitate elem) {
        String s = elem.getId() + ";" + elem.getData() + ";" + elem.getNrPasi() + ";" + elem.getNrOreSomn();
        for (Actiune actiune : elem.getActiuni()) {
            s += ";" + actiune.getDescriere() + "," + actiune.getDurata();
        }
        return s;
    }
}
