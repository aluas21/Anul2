package com.example.examen2;

import com.example.examen2.controllers.MainController;
import com.example.examen2.domain.*;
import com.example.examen2.repository.IRepository;
import com.example.examen2.repository.TextRepository;
import com.example.examen2.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    static IRepository<Activitate> repoActivitate;
    static ConverActivitate activitateConvertUnu = new ConverActivitate();
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
            repoActivitate = new TextRepository<>(setari.getRepoFile1(), activitateConvertUnu);
        }

        if (Objects.equals(setari.getRepoType(), "bin")) {

        }

        if (Objects.equals(setari.getRepoType(), "db")) {

        }

        Service service = new Service(repoActivitate);

        MainController pacientController = fxmlLoader.getController();
        pacientController.setService(service);

    }



    public static void main(String[] args) {
        launch();
    }
}