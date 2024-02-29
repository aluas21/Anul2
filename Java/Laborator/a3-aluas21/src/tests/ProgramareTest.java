package tests;

import domain.Pacient;
import domain.Programare;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramareTest {

    @Test
    void testAll() {
        testToString();
        SetGetId();
        SetGetPacient();
        SetGetMotiv();
        SetGetDate();
        SetGetTime();
        testEquals();
        testHasCode();
    }


    @Test
    void testHasCode(){
        Pacient persoana1 = new Pacient(1,"John", "Doe", 25);
        Pacient persoana2 = new Pacient(2,"Jane", "Doe", 30);

        Programare programare1 = new Programare(1,persoana1,  LocalDate.of(2023, 1, 1), LocalTime.of(10, 0),"Consultație");
        Programare programare2 = new Programare(2,persoana2, LocalDate.of(2023, 1, 2), LocalTime.of(14, 30),"Analize");

        // Calculați hash-urile
        int hash1 = Objects.hash(programare1.getPacient(), programare1.getMotiv(), programare1.getDate(), programare1.getTime());
        int hash2 = Objects.hash(programare2.getPacient(), programare2.getMotiv(), programare2.getDate(), programare2.getTime());

        // Verificați că hash-urile sunt calculate corect
        assertEquals(hash1, programare1.hashCode());
        assertEquals(hash2, programare2.hashCode());
    }
    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        String expectedToString = "Programare{pacient=Pacient{name='John', surname='Doe', age=30, id=1}, " +
                "motiv='Regular checkup', date=2023-01-01, time=12:30, id=1}";
        assertEquals(expectedToString, programare.toString());
    }

    @Test
    void SetGetId() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals(1, programare.getId());
        programare.setId(2);
        assertEquals(2, programare.getId());
    }

    @Test
    void SetGetPacient() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals(pacient, programare.getPacient());
        Pacient newPacient = new Pacient(2, "Alice", "Smith", 25);
        programare.setPacient(newPacient);
        assertEquals(newPacient, programare.getPacient());
    }

    @Test
    void SetGetMotiv() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals("Regular checkup", programare.getMotiv());
        programare.setMotiv("Consultation");
        assertEquals("Consultation", programare.getMotiv());
    }

    @Test
    void SetGetDate() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals(date, programare.getDate());
        LocalDate newDate = LocalDate.of(2023, 2, 1);
        programare.setDate(newDate);
        assertEquals(newDate, programare.getDate());
    }

    @Test
    void SetGetTime() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);
        Programare programare = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals(time, programare.getTime());
        LocalTime newTime = LocalTime.of(14, 0);
        programare.setTime(newTime);
        assertEquals(newTime, programare.getTime());
    }

    @Test
    void testEquals() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalTime time = LocalTime.of(12, 30);

        Programare programare1 = new Programare(1, pacient, date, time, "Regular checkup");
        Programare programare2 = new Programare(1, pacient, date, time, "Regular checkup");

        assertEquals(programare1, programare2);
    }
}
