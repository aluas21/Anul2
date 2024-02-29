package tests;

import domain.Pacient;
import domain.Programare;
import exception.RepositoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BinaryRepository;
import repository.IRepository;
import repository.Repository;
import service.ServicePacient;
import service.ServiceProgramari;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServicePacientTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    IRepository<Pacient> repo = new Repository<Pacient>();
    IRepository<Programare> repoProg = new Repository<Programare>();

    ServiceProgramari serviceProgramari = new ServiceProgramari(repoProg,repo);
    ServicePacient service = new ServicePacient(repo,serviceProgramari);

    void setUp() throws RepositoryException {
        service.addPacient(1,"Alin","Alin",19);
        service.addPacient(2,"Ana","Ana",19);
        service.addPacient(3,"Alex","Alex",19);
        LocalTime time = LocalTime.parse("21:30",formatter2);
        LocalDate date = LocalDate.parse("2022/11/22",formatter);
        serviceProgramari.addProgramare(1,1,date,time,"bolnav");
        serviceProgramari.addProgramare(2,2,date,time,"bolnav");
        serviceProgramari.addProgramare(3,3,date,time,"bolnav");
    }

    void tearDown() {
        ArrayList<Integer> idPacienti = new ArrayList<Integer>();
        for(Pacient pacient: service.getAll())
            idPacienti.add(pacient.getId());

        for(int id: idPacienti)
            try {
                service.deletePacient(id);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        ArrayList<Integer> idProgramari = new ArrayList<Integer>();
        for(Programare programare: serviceProgramari.getAll())
            idProgramari.add(programare.getId());
        for(int id: idProgramari)
            try {
                serviceProgramari.deleteProgramareById(id);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
    }

    @Test
    void getAll() {
        addPacient();
        deletePacient();
        updatePacient();
        addProgramare();
    }

    @Test
    void addProgramare(){
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(serviceProgramari.getAll().size(),3);
        try {
            serviceProgramari.addProgramare(4,1,LocalDate.parse("2022/11/22",formatter),LocalTime.parse("21:30",formatter2),"bolnav");
            assert true;
        } catch (RepositoryException e) {
            assert false;
        }
        assertEquals(serviceProgramari.getAll().size(),4);
        try {
            serviceProgramari.addProgramare(4,1,LocalDate.parse("2022/11/22",formatter),LocalTime.parse("21:30",formatter2),"bolnav");
            assert false;
        } catch (RepositoryException e) {
            assert true;
        }

        try{
            serviceProgramari.addProgramare(4,101,LocalDate.parse("2022/11/22",formatter),LocalTime.parse("21:30",formatter2),"bolnav");
            assert false;
        } catch (RepositoryException | RuntimeException e) {
            assert true;
        }
        tearDown();
    }
    @Test
    void addPacient() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(service.getAll().size(),3);
        try {
            service.addPacient(4,"Alin","Alin",19);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(service.getAll().size(),4);
        try {
            service.addPacient(4,"Alin","Alin",19);
            assert false;
        } catch (RepositoryException e) {
            assert true;
        }
        tearDown();
    }

    @Test
    void deletePacient() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(service.getAll().size(),3);
        try {
            service.deletePacient(1);
            assert true;
        } catch (RepositoryException e) {
            assert false;
        }
        try{
            service.deletePacient(1);
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }
        assertEquals(service.getAll().size(),2);
        tearDown();
    }

    @Test
    void updatePacient() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(service.getAll().size(),3);
        try {
            service.updatePacient(1,"Alin","Alin",19);
            assert true;
        } catch (RepositoryException e) {
            assert false;
        }
        try{
            service.updatePacient(4,"Alin","Alin",19);
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }
        assertEquals(service.getAll().size(),3);
        tearDown();
    }
}