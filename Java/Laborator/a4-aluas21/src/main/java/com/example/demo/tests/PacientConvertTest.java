package com.example.demo.tests;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.PacientConvert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PacientConvertTest {
    @org.junit.Test
    public void testAll(){
        testFromString();
        testToString();
        testFromStringWithInvalidInput();
    }

    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        PacientConvert pacientConvert = new PacientConvert();
        String expectedString = "1 John Doe 30";
        assertEquals(expectedString, pacientConvert.ToString(pacient));
    }

    @Test
    void testFromString() {
        String inputString = "1 John Doe 30";
        PacientConvert pacientConvert = new PacientConvert();
        Pacient expectedPacient = new Pacient(1, "John", "Doe", 30);
        Pacient actualPacient = pacientConvert.fromString(inputString);
        assertEquals(expectedPacient, actualPacient);
    }

    @Test
    void testFromStringWithInvalidInput() {
        String invalidInputString = "invalid_input";
        PacientConvert pacientConvert = new PacientConvert();

        // In this case, fromString should return null or handle the error gracefully
        assertThrows(NumberFormatException.class, () -> pacientConvert.fromString(invalidInputString));
    }
}