package com.example.examen1;

import com.example.examen1.controllers.MainController;
import com.example.examen1.domain.Entity;
import com.example.examen1.domain.EntityConvert;
import com.example.examen1.domain.Produs;
import com.example.examen1.repository.BinaryRepository;
import com.example.examen1.repository.IRepository;
import com.example.examen1.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    static IRepository<Produs> repoProdus = null;
    static Settings setari = Settings.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("APPLICATION");
        stage.setScene(scene);
        stage.show();



        if (Objects.equals(setari.getRepoType(), "bin")) {
            repoProdus = new BinaryRepository<>(setari.getRepoFile1());
            System.out.println("bin");
        }

        //TO DO: de setat service-ul
        //       de adugat repo-ul in service

//        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);
//        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);


        MainController ProdusController = fxmlLoader.getController();

        Service service = new Service(repoProdus);
        ProdusController.setService(service);
    }

    public static void main(String[] args) {
        launch();
    }
}