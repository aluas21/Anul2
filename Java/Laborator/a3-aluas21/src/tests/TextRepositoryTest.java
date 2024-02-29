package tests;

import domain.Pacient;
import domain.PacientConvert;
import domain.Programare;
import exception.RepositoryException;
import org.junit.jupiter.api.Test;
import repository.IRepository;
import repository.Repository;
import repository.TextRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TextRepositoryTest {

    @org.junit.Test
    public void testAll(){
        update();
        addEntity();
        deleteById();
        TestFile();

    }
    PacientConvert pacientConvert = new PacientConvert();

    @Test
    void TestFile(){
        try{
            IRepository<Pacient> repository = new TextRepository<>("RepoError.txt",pacientConvert);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

        try{
            IRepository<Pacient> repository = new TextRepository<>("DontExist",pacientConvert);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

    }

    private void Innit(IRepository<Pacient> repository){
        Pacient pacient1 = new Pacient(2, "John", "Doe", 30);
        Pacient pacient2 = new Pacient(3, "John", "Doe", 30);
        Pacient pacient3 = new Pacient(4, "John", "Doe", 30);
        try {
            repository.addEntity(pacient1);
            repository.addEntity(pacient2);
            repository.addEntity(pacient3);
            assert true;

        } catch (RepositoryException e) {
            assert false;
        }
    }

    private void ClearRepo(){
        try (FileWriter fw = new FileWriter("RepoTest.txt")) {
            Pacient pacient = new Pacient(1, "John", "Doe", 30);
            fw.write(pacientConvert.ToString(pacient));
            assert true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void addEntity() {
        IRepository<Pacient> repository = new TextRepository("RepoTest.txt",pacientConvert);
        ClearRepo();
        Innit(repository);
        try{
            repository.addEntity(new Pacient(1,"1","1",23));
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }

        try{
            Pacient pacient3 = new Pacient(9, "John", "Doe", 30);
            repository.addEntity(pacient3);
            assert true;
        }
        catch (RepositoryException e){
            assert false;
        }
        ClearRepo();

    }

    @Test
    void deleteById() {
        IRepository<Pacient> repository = new TextRepository<>("RepoTest.txt",pacientConvert);
        ClearRepo();
        assertEquals(repository.getSize() ,1);
        Innit(repository);
        try{
            repository.deleteById(1);
            assert true;
        }
        catch (RepositoryException e){
            assert false;
        }

        try{
            repository.deleteById(99);
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }
        ClearRepo();
        try{
            IRepository<Pacient> repository1 = new TextRepository<>("RepoTest.txt",pacientConvert);
            assertEquals(repository1.getSize(),1);
            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }
    }

    @Test
    void update() {
        ClearRepo();
        IRepository<Pacient> repository = new TextRepository<>("RepoTest.txt",pacientConvert);
        assertEquals(repository.getSize(),1);
        Innit(repository);
        try {
            Pacient pacient4 = new Pacient(2, "John122", "Doe", 30);
            repository.update(pacient4);
            assert true;

        } catch (RepositoryException e) {
            assert false;
        }
        try {
            Pacient pacient3 = new Pacient(101, "John1", "Doe", 30);
            repository.update(pacient3);
            assert false;

        } catch (RepositoryException e) {
            assert true;
        }
        ClearRepo();
        try{
            IRepository<Pacient> repository1 = new TextRepository<>("RepoTest.txt",pacientConvert);
            assertEquals(repository1.getSize(),1);
            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }
    }
}