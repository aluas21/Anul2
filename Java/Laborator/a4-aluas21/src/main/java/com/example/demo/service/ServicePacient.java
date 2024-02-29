package com.example.demo.service;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.domain.dto.PacientNrProg;
import com.example.demo.domain.dto.PacientZileProg;
import com.example.demo.exception.RepositoryException;
import com.example.demo.repository.IRepository;
import com.example.demo.validari.ValidariPacient;

import java.util.ArrayList;

public class ServicePacient {
    IRepository<Pacient> repo;
    ServiceProgramari serviceProgramari;


    public ServicePacient(IRepository<Pacient> repo,ServiceProgramari serviceProgramari) {
        this.repo = repo;
        this.serviceProgramari = serviceProgramari;
    }

    public ArrayList<Pacient> getAll(){
        return repo.getAll();
    }

    public void addPacient(int id, String name, String surname, int age) throws RepositoryException {
        Pacient pacient = new Pacient(id,name,surname,age);
        ValidariPacient validariPacient = new ValidariPacient(pacient);
        validariPacient.validari();
        repo.addEntity(pacient);
    }

    public void deletePacient(int id) throws RepositoryException {

        ArrayList<Integer> programareIds = new ArrayList<Integer>();

        ArrayList<Programare> programari = serviceProgramari.getAll();
        for(Programare programare : programari)
            if(programare.getPacient().getId() == id){
                programareIds.add(programare.getId());
            }
        for(Integer idprog: programareIds)
            serviceProgramari.deleteProgramareById(idprog);
        repo.deleteById(id);
    }

    public void updatePacient(int id, String newName, String newSurname, int newAge) throws RepositoryException {
        Pacient pacient = new Pacient(id,newName,newSurname,newAge);
        repo.update(pacient);
        ArrayList<Integer> programareIds = new ArrayList<Integer>();

        ArrayList<Programare> programari = serviceProgramari.getAll();
        for(Programare programare : programari)
            if(programare.getPacient().getId() == id){
                programareIds.add(programare.getId());
            }
        for(Integer idprog: programareIds)
            serviceProgramari.updateProgramare(pacient);
    }

    public ArrayList<PacientNrProg> sortbynrptog(){
        ArrayList<PacientNrProg> list = new ArrayList<>();
        for(Pacient p : repo.getAll()){
            list.add(new PacientNrProg(p, serviceProgramari.getAll().stream().filter(x->x.getPacient().getId()==p.getId()).toArray().length));
        }
        list.sort((x,y)->y.getNrProg()-x.getNrProg());
        return list;
    }

    //get for pacient by id the days between now and last programare DATE
    public int getDaysAfterLastProg(int id){
        ArrayList<Programare> programari = serviceProgramari.getAll();
        int max = 0;
        for(Programare programare : programari)
            if(programare.getPacient().getId() == id){
                if(programare.getDate().getDayOfYear() > max)
                    max = programare.getDate().getDayOfYear();
            }
        return max;
    }

    //sort pacienti by days after last programare
    public ArrayList<PacientZileProg> sortbyzileptog(){
        ArrayList<Pacient> list = new ArrayList<>();
        for(Pacient p : repo.getAll()){
            list.add(p);
        }
        ArrayList<PacientZileProg> list2 = new ArrayList<>();
        for(Pacient p : list){
            list2.add(new PacientZileProg(p, getDaysAfterLastProg(p.getId())));
        }
//        for(int i = 0;i<list2.size();i++)
//            System.out.println(list2.get(i).getDays());
        list2.sort((x,y)->y.getDays()-x.getDays());
        return list2;
    }

    public Pacient getById(int id){
        return repo.getById(id);
    }
}
