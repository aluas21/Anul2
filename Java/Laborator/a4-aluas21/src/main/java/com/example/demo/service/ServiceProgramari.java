package com.example.demo.service;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.domain.dto.ProgramariLuna;
import com.example.demo.exception.RepositoryException;
import com.example.demo.repository.IRepository;
import com.example.demo.validari.ValidariProgramari;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ServiceProgramari {
    IRepository<Programare> repo;
    IRepository<Pacient> repoPacient;//trebuie pentru adugre

    public ServiceProgramari(IRepository<Programare> repo, IRepository<Pacient> repoPacient){
        this.repo = repo;
        this.repoPacient = repoPacient;
    }

    public ArrayList<Programare> getAll(){
        return repo.getAll();
    }

    public void addProgramare(int idProgramare, int idPacient, LocalDate data, LocalTime time, String motiv) throws RepositoryException {
        if(!repoPacient.findByID(idPacient))
            throw new RuntimeException("Pacietul nu a fost gasit");

        Programare programare = new Programare(idProgramare, repoPacient.getById(idPacient), data, time, motiv);
        ValidariProgramari validariProgramari = new ValidariProgramari(programare);
        validariProgramari.validari();
        repo.addEntity(programare);
    }

    public void deleteProgramareById(int idProgramare) throws RepositoryException {
        repo.deleteById(idProgramare);
    }


    public void updateProgramare(Pacient pacient) throws RepositoryException {
        ArrayList<Programare> programares = repo.getAll();
        for(Programare programare: programares)
            if(programare.getPacient().getId() == pacient.getId()){
                Programare programare1 = new Programare();
                programare1.setId(programare.getId());
                programare1.setDate(programare.getDate());
                programare1.setMotiv(programare.getMotiv());
                programare1.setPacient(pacient);
                programare1.setTime(programare.getTime());
                repo.update(programare1);
            }
    }

    public ArrayList<ProgramariLuna> countProgramariLuna(){
        ArrayList<ProgramariLuna> list = new ArrayList<>();
        for(int i = 1;i<=12;i++){
            int finalI = i;
            list.add(new ProgramariLuna(i, repo.getAll().stream().filter(x->x.getDate().getMonthValue()== finalI).toArray().length));
        }
        return list;
    }
}
