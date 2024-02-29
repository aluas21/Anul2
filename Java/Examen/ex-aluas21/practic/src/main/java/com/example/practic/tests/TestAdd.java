package com.example.practic.tests;

import com.example.practic.domain.Quiz;
import com.example.practic.repository.IRepository;
import com.example.practic.repository.RepoQuizDB;
import com.example.practic.service.Service;

public class TestAdd {
    //test adding a quiz in repository
    public void testAdd() {
        Service service = new Service(new RepoQuizDB());
        Quiz quiz2 = new Quiz(1, "text", "raspunsA", "raspunsB", "raspunsC", "raspunsCorect", 10);
        try {
            service.addEntity(1, "text", "raspunsA", "raspunsB", "raspunsC", "raspunsCorect", 10);
        } catch (Exception e) {
            assert false;
        }
        assert true;

        //add duplicate id
        try {
            service.addEntity(1, "text", "raspunsA", "raspunsB", "raspunsC", "raspunsCorect", 10);
        } catch (Exception e) {
            assert true;
        }
        IRepository<Quiz> repo = new RepoQuizDB();
        Quiz quiz = new Quiz(1, "text", "raspunsA", "raspunsB", "raspunsC", "raspunsCorect", 10);
        try {
            repo.addEntity(quiz);
        } catch (Exception e) {
            assert false;
        }
        assert true;
        //add duplicate id
        try {
            repo.addEntity(quiz);
        } catch (Exception e) {
            assert true;
        }


    }
}
