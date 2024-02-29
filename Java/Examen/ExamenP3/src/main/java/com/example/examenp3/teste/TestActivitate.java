package com.example.examenp3.teste;

import com.example.examenp3.domain.Activitate;
import org.junit.Test;

import java.time.LocalDate;


public class TestActivitate {
    //testeaza fiecare metoda a clasei Activitate
    @org.junit.Test
    public void testAll(){
        testConstructor();
        testGetId();
        testSetId();
        testGetData();
        testSetData();
        testGetNrPasi();
        testSetNrPasi();

    }

    @Test
    public void testConstructor(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        assert a.getId() == 1;
        assert a.getData() == null;
        assert a.getNrPasi() == 0;
        assert a.getDescriere() == null;
        assert a.getDurata() == 0;
    }

    @Test
    public void testGetId(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        assert a.getId() == 1;
    }

    @Test
    public void testSetId(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        a.setId(2);
        assert a.getId() == 2;
    }
    @Test
    public void testGetData(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        assert a.getData() == null;
    }
    @Test
    public void testSetData(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        a.setData(LocalDate.parse("2022-10-10"));
        assert a.getData().equals(LocalDate.parse("2022-10-10"));
    }
    @Test
    public void testGetNrPasi(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        assert a.getNrPasi() == 0;
    }
    @Test
    public void testSetNrPasi(){
        Activitate a = new Activitate(1, null, 0, null, 0);
        a.setNrPasi(1);
        assert a.getNrPasi() == 1;
    }
}
