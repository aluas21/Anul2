package service;

import domain.Pacient;
import domain.Programare;
import exception.DuplicateIdException;
import exception.NotFoundId;
import exception.RepositoryException;
import repository.IRepository;
import repository.Repository;
import validari.ValidariPacient;

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
}
