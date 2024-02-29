package com.example.examenp3;

import com.example.examenp3.controllers.MainController;
import com.example.examenp3.domain.Activitate;
import com.example.examenp3.domain.Entity;
import com.example.examenp3.domain.EntityConvert;
import com.example.examenp3.repository.IRepository;
import com.example.examenp3.repository.RepoActivitatiDB;
import com.example.examenp3.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    static IRepository<Activitate> repo = null;
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
            repo = new  RepoActivitatiDB();
        }


//        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);
//        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);

        Service service = new Service(repo);
//
        MainController pacientController = fxmlLoader.getController();
        pacientController.setService(service);

    }



    public static void main(String[] args) {
        launch();
    }
}