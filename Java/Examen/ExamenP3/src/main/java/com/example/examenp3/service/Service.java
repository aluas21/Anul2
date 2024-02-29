package com.example.examenp3.service;

import com.example.examenp3.domain.Activitate;
import com.example.examenp3.domain.Entity;
import com.example.examenp3.exception.RepositoryException;
import com.example.examenp3.repository.IRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Service {
    IRepository<Activitate> repo;

    public Service(IRepository<Activitate> repo) {
        this.repo = repo;
    }

    public ArrayList<Activitate> sortbyNrPasi(){
        ArrayList<Activitate> activitati = repo.getAll();
        activitati.sort(Comparator.comparingInt(Activitate::getNrPasi));
        return activitati;
    }

    public ArrayList<Activitate> getAll() {
        return repo.getAll();
    }

    public void addActivitate(int id, LocalDate date, int nrPasi, String descriere, int durata) throws RepositoryException {
        Activitate activitate = new Activitate(id,date,nrPasi,descriere,durata);
        repo.addEntity(activitate);
    }

    public void filtrare(String filtru) throws RepositoryException {
        //delete all elements where filtru is find in nume or marca, ignoring capital letters
        int countProd = 0;
        ArrayList<Activitate> produse = repo.getAll();
        for(Activitate produs : produse)
            if(produs.getDescriere().toLowerCase().contains(filtru.toLowerCase()))
                countProd++;
        if(countProd == repo.getSize())
            throw new RepositoryException("O SA RAMANA 0 PRODUSE");
        for(Activitate produs : produse)
            if(produs.getDescriere().toLowerCase().contains(filtru.toLowerCase()))
                try {
                    repo.deleteById(produs.getId());
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
    }
}
