package com.example.examen2.service;

import com.example.examen2.domain.Actiune;
import com.example.examen2.domain.Activitate;
import com.example.examen2.domain.Entity;
import com.example.examen2.exception.RepositoryException;
import com.example.examen2.repository.IRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Service {
    IRepository<Activitate> repo;

    public Service(IRepository<Activitate> repo) {
        this.repo = repo;
    }

    public ArrayList<Activitate> sortByDate() {
        ArrayList<Activitate> activitati = repo.getAll();
        activitati.sort((Activitate a1, Activitate a2) -> a1.getData().compareTo(a2.getData()));
        return activitati;
    }

    public void addActivitate(int maxId, LocalDate data, String nrPasi, String descriere, String durata) throws RepositoryException {
        ArrayList<Actiune> actiuni = new ArrayList<>();
        actiuni.add(new Actiune(descriere, Integer.parseInt(durata)));
        Activitate activitate = new Activitate(maxId + 1, data, Integer.parseInt(nrPasi), 0, actiuni);
        for (Activitate a : repo.getAll()) {
            if (a.getData().equals(data)) {
                a.setNrPasi(a.getNrPasi() + Integer.parseInt(nrPasi));
                a.setNrOreSomn(a.getNrOreSomn() + Integer.parseInt(durata));
                a.getActiuni().add(new Actiune(descriere, Integer.parseInt(durata)));
                repo.update(a);
                return;
            }
        }
    }

    public ArrayList<Activitate> getActivitati(){
        ArrayList<Activitate> activitati = new ArrayList<>();
        for (Activitate a : repo.getAll()) {
            if (a.getActiuni().size() > 1) {
                for (Actiune actiune : a.getActiuni()) {
                    ArrayList<Actiune> actiuni = new ArrayList<>();
                    actiuni.add(actiune);
                    activitati.add(new Activitate((int) (Math.random() * 1000), a.getData(), a.getNrPasi(), a.getNrOreSomn(), actiuni));
                }
            } else {
                activitati.add(a);
            }
        }
        activitati.sort(Comparator.comparing(Activitate::getData));

        return activitati;
    }
}
