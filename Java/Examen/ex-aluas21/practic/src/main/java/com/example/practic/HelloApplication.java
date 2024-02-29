package com.example.practic;

import com.example.practic.controllers.MainController;
import com.example.practic.domain.EntityConvert;
import com.example.practic.domain.Quiz;
import com.example.practic.repository.IRepository;
import com.example.practic.repository.RepoQuizDB;
import com.example.practic.service.Service;
import com.example.practic.tests.TestAdd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    static IRepository<Quiz> repoUNU = null;
    static EntityConvert entityConvertUnu = null;
    static Settings setari = Settings.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("APPLICATION");
        stage.setScene(scene);
        stage.show();



        if (Objects.equals(setari.getRepoType(), "memory")) {

        }

        if (Objects.equals(setari.getRepoType(), "text")) {

        }

        if (Objects.equals(setari.getRepoType(), "bin")) {

        }

        if (Objects.equals(setari.getRepoType(), "db")) {
            repoUNU = new RepoQuizDB();

        }


//        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);
//        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);

        Service service = new Service(repoUNU);

        MainController pacientController = fxmlLoader.getController();
        pacientController.setService(service);

    }



    public static void main(String[] args) {
        TestAdd testAdd = new TestAdd();
        testAdd.testAdd();
        launch();
    }
}