package com.example.practic.service;

import com.example.practic.domain.Entity;
import com.example.practic.domain.Quiz;
import com.example.practic.domain.QuizDTO;
import com.example.practic.exception.RepositoryException;
import com.example.practic.repository.IRepository;

import java.util.ArrayList;

public class Service {
    IRepository<Quiz> repo;

    public Service(IRepository<Quiz> repo) {
        this.repo = repo;
    }

    public ArrayList<Quiz> getAll(){
        return repo.getAll();
    }

    public void addEntity(int i, String text, String raspunsA, String raspunsB, String raspunsC, String raspunsCorect, int punctaj) throws RepositoryException {
        Quiz p = new Quiz(i, text, raspunsA, raspunsB, raspunsC, raspunsCorect, punctaj);
        repo.addEntity(p);

    }

    public ArrayList<QuizDTO> generateQuiz(int punctajMin,int punctaMax, int nrIntrebari){
        ArrayList<QuizDTO> lista = new ArrayList<>();
        ArrayList<Quiz> lista2 = repo.getAll();
        for(Quiz p : lista2){
            if(p.getPunctaj() >= punctajMin && p.getPunctaj() <= punctaMax){
                QuizDTO dto = new QuizDTO(p.getId(), p.getText(), p.getPunctaj());
                lista.add(dto);
                nrIntrebari--;
            }
            if(nrIntrebari == 0){
                break;
            }
        }
        return lista;
    }

    public ArrayList<QuizDTO> getAllDTO(){
        ArrayList<QuizDTO> lista = new ArrayList<>();
        for(Quiz p : repo.getAll()){
            QuizDTO dto = new QuizDTO(p.getId(), p.getText(), p.getPunctaj());
            lista.add(dto);
        }
        lista.sort((o1, o2) -> o1.getId() - o2.getId());
        return lista;
    }
}
