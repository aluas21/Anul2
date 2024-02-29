package com.example.demo.tests;

import com.example.demo.domain.Pacient;
import com.example.demo.exception.RepositoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.repository.BinaryRepository;
import com.example.demo.repository.IRepository;
import com.example.demo.repository.Repository;
import com.example.demo.repository.TextRepository;

import javax.naming.InitialContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryRepositoryTest {
    private static final String TEST_FILE_PATH = "RepoBin.txt";
    private BinaryRepository<Pacient> repository;

    void setUp() throws RepositoryException {
        // Initialize the repository and create an initial file for testing
        try {
            Files.deleteIfExists(Path.of(TEST_FILE_PATH));
        } catch (IOException e) {
            throw new RepositoryException("Error deleting test file " + TEST_FILE_PATH,e);
        }
        repository = new BinaryRepository<Pacient>(TEST_FILE_PATH);
        repository.addEntity(new Pacient(1001, "one", "one", 1));
        repository.addEntity(new Pacient(2001, "two", "two", 2));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test file after each test
        Files.deleteIfExists(Path.of(TEST_FILE_PATH));

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
    @org.junit.Test
    public void testAll(){
        addEntity();
        deleteById();
        update();
        TestError();
    }

    @Test
    void TestError(){
        try{
            IRepository<Pacient> repository = new BinaryRepository<>(null);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

        try{
            IRepository<Pacient> repository = new BinaryRepository<>("RepoError.txt");
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }
    }

    @Test
    void addEntity() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(repository.getSize(),2);
        Innit(repository);
        assertEquals(repository.getSize(),6);
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



//        IRepository<Pacient> repository = new BinaryRepository<>("RepoBin.txt");
//        System.out.println(repository.getSize());
//        assertEquals(repository.getSize(),0);
//        Innit(repository);
//        assertEquals(repository.getSize(),4);
//        try{
//            repository.addEntity(new Pacient(1,"1","1",23));
//            assert false;
//        }
//        catch (RepositoryException e){
//            assert true;
//        }
//
//        try{
//            Pacient pacient3 = new Pacient(9, "John", "Doe", 30);
//            repository.addEntity(pacient3);
//            assert true;
//        }
//        catch (RepositoryException e){
//            assert false;
//        }
    }

    @Test
    void deleteById() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
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


    }

    @Test
    void update() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
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
}