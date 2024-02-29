package com.example.demo.tests;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.domain.ProgramareConvert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramareConvertTest {
    @org.junit.Test
    public void testAll(){
        testFromString();
        testToString();
    }

    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse("12:30", DateTimeFormatter.ofPattern("H:mm"));
        Programare programare = new Programare(1, pacient, date, time, "Regular");

        ProgramareConvert programareConvert = new ProgramareConvert();
        String expectedString = "1 1 John Doe 30 Regular 2023-01-01 12:30";
        assertEquals(expectedString, programareConvert.ToString(programare));
    }

    @Test
    void testFromString() {
        String inputString = "1 1 John Doe 30 Regular 2023-01-01 12:30";
        ProgramareConvert programareConvert = new ProgramareConvert();
        Programare expectedProgramare = new Programare();
        expectedProgramare.setId(1);
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        expectedProgramare.setPacient(pacient);
        expectedProgramare.setMotiv("Regular");
        expectedProgramare.setDate(LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        expectedProgramare.setTime(LocalTime.parse("12:30", DateTimeFormatter.ofPattern("H:mm")));

        Programare actualProgramare = programareConvert.fromString(inputString);
        assertEquals(expectedProgramare, actualProgramare);
    }
}
