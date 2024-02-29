package tests;

import domain.Pacient;
import exception.RepositoryException;
import org.junit.jupiter.api.Test;
import repository.IRepository;
import repository.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class RepositoryTest {

    @org.junit.Test
    public void testAll(){
        getSize();
        getAt();
        getAll();
        update();
        getById();
        addEntity();
        deleteById();
    }


    private void Innit(IRepository<Pacient> repository){
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        Pacient pacient1 = new Pacient(2, "John", "Doe", 30);
        Pacient pacient2 = new Pacient(3, "John", "Doe", 30);
        Pacient pacient3 = new Pacient(4, "John", "Doe", 30);
        try {
            repository.addEntity(pacient);
            repository.addEntity(pacient1);
            repository.addEntity(pacient2);
            repository.addEntity(pacient3);
            assert true;

        } catch (RepositoryException e) {
            assert false;
        }
    }

    @Test
    void getSize() {
        IRepository<Pacient> repository = new Repository<>();
        Innit(repository);
        assertEquals(repository.getSize(),4);
    }

    @Test
    void getAt() {
        IRepository<Pacient> repository = new Repository<>();
        Innit(repository);
        assertEquals(repository.getAt(0).getId(),1);
    }

    @Test
    void findByID() {
        IRepository<Pacient> repository = new Repository<>();
        Innit(repository);
        assertEquals(repository.findByID(1),true);
        assertEquals(repository.findByID(3),true);
        assertEquals(repository.findByID(88),false);
    }

    @Test
    void getById() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        Pacient pacient2 = new Pacient(3, "John", "Doe", 30);
        IRepository<Pacient> repository = new Repository<>();
        Innit(repository);
        assertEquals(repository.getById(1),pacient);
        assertEquals(repository.getById(3),pacient2);
        assertNull(repository.getById(88));


    }

    @Test
    void deleteById() {
        IRepository<Pacient> repository = new Repository<>();
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
        for(Pacient pacient:repository){
            assert true;
        }

    }

    @Test
    void addEntity() {
       IRepository<Pacient> repository = new Repository<>();
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
    }

    @Test
    void update() {
        IRepository<Pacient> repository = new Repository<>();
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
    }

    @Test
    void getAll() {
        Pacient pacient = new Pacient(1, "John", "Doe", 30);
        Pacient pacient1 = new Pacient(2, "John", "Doe", 30);
        Pacient pacient2 = new Pacient(3, "John", "Doe", 30);
        Pacient pacient3 = new Pacient(4, "John", "Doe", 30);
        IRepository<Pacient> repository = new Repository<>();
        Innit(repository);
        ArrayList<Pacient> listPacienti = repository.getAll();
        assertEquals(listPacienti.get(0),pacient);
        assertEquals(listPacienti.get(1),pacient1);
        assertEquals(listPacienti.get(2),pacient2);
        assertEquals(listPacienti.get(3),pacient3);
    }
}
