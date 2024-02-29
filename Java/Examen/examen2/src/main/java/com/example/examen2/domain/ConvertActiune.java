package com.example.examen2.domain;

public class ConvertActiune implements EntityConvert<Actiune> {
    @Override
    public Actiune fromString(String line) {
        String[] fields = line.split(",");
        String descriere = fields[0];
        int durata = Integer.parseInt(fields[1]);
        return new Actiune(descriere, durata);
    }

    @Override
    public String ToString(Actiune elem) {
        return elem.getDescriere() + "," + elem.getDurata();
    }
}
