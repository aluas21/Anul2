package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProgramareConvert implements EntityConvert<Programare> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public String ToString(Programare elem) {
        PacientConvert pacientConvert = new PacientConvert();
        return elem.getId() + " " + pacientConvert.ToString(elem.getPacient()) + " " + elem.getMotiv() + " " + elem.getDate() + " " + elem.getTime();
    }

    @Override
    public Programare fromString(String line) {
        String[] attributes = line.split(" ");
        Programare programare = new Programare();
        programare.setId(Integer.parseInt(attributes[0]));
        Pacient pacient = new Pacient();
        pacient.setId(Integer.parseInt(attributes[1]));
        pacient.setName(attributes[2]);
        pacient.setSurname(attributes[3]);
        pacient.setAge(Integer.parseInt(attributes[4]));
        programare.setPacient(pacient);
        programare.setMotiv(attributes[5]);
        programare.setDate(LocalDate.parse(attributes[6]));
        programare.setTime(LocalTime.parse(attributes[7]));
        return programare;
    }

}
