package com.example.examen1.service;

import com.example.examen1.domain.Entity;
import com.example.examen1.domain.Produs;
import com.example.examen1.exception.RepositoryException;
import com.example.examen1.repository.IRepository;

import java.util.ArrayList;

public class Service {
    IRepository<Produs> repository;

    public Service(IRepository<Produs> repository) {
        this.repository = repository;
    }

    public void addProdus(int id, String nume, String producator, int pret, int cantitate) throws RepositoryException {
        Produs produs = new Produs(id, nume, producator, pret, cantitate);
        repository.addEntity(produs);
    }

    public ArrayList<Produs> getAll(){
        return repository.getAll();
    }

    public void filtrare(String filtru) throws RepositoryException {
        //delete all elements where filtru is find in nume or marca, ignoring capital letters
        int countProd = 0;
        ArrayList<Produs> produse = repository.getAll();
        for(Produs produs : produse)
            if(produs.getNume().toLowerCase().contains(filtru.toLowerCase()) || produs.getMarca().toLowerCase().contains(filtru.toLowerCase()))
                countProd++;
        if(countProd == repository.getSize())
            throw new RepositoryException("O SA RAMANA 0 PRODUSE");
        for(Produs produs : produse)
            if(produs.getNume().toLowerCase().contains(filtru.toLowerCase()) || produs.getMarca().toLowerCase().contains(filtru.toLowerCase()))
                try {
                    repository.deleteById(produs.getId());
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
    }
}
