package com.example.demo.tests;

import com.example.demo.domain.Pacient;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PacientTest{

    @org.junit.Test
    public void testAll() {
        testToString();
        SetGetId();
        SetGetName();
        SetGetAge();
        SetGetSurname();
        testEquals();
        testHashCode();
    }

    @Test
    void testHashCode(){
        Pacient persoana1 = new Pacient(1,"John", "Doe", 25);
        Pacient persoana2 = new Pacient(2,"Jane", "Doe", 30);

        // Calculați hash-urile
        int hash1 = Objects.hash(persoana1.getName(), persoana1.getSurname(), persoana1.getAge());
        int hash2 = Objects.hash(persoana2.getName(), persoana2.getSurname(), persoana2.getAge());

        // Verificați că hash-urile sunt calculate corect
        assertEquals(hash1, persoana1.hashCode());
        assertEquals(hash2, persoana2.hashCode());
    }
    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        String expectedToString = "Pacient{name='John', surname='Doe', age=30, id=1}";
        assertEquals(expectedToString, pacient.toString());
    }

    void SetGetId() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        assertEquals(1,pacient.getId());
        pacient.setId(2);
        assertEquals(2,pacient.getId());
        Pacient pacient1 = new Pacient(1);
        assertEquals(1,pacient1.getId());


    }

    @Test
    void SetGetName() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        assertEquals("John",pacient.getName());
        pacient.setName("Ana");
        assertEquals("Ana",pacient.getName());
    }

    @Test
    void SetGetAge() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        assertEquals(pacient.getAge(),30);
        pacient.setAge(10);
        assertEquals(pacient.getAge(),10);
    }

    @Test
    void SetGetSurname() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        assertEquals("Doe",pacient.getSurname());
        pacient.setSurname("Ana");
        assertEquals("Ana",pacient.getSurname());
    }

    @Test
    void testEquals() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        Pacient pacient1 = new Pacient(1, "John", "Doe", 30);
        assertEquals(pacient,pacient1);
    }

}