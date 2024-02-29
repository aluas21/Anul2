package com.example.demo;

import com.example.demo.controllers.ClinicaController;
import com.example.demo.domain.Pacient;
import com.example.demo.domain.PacientConvert;
import com.example.demo.domain.Programare;
import com.example.demo.domain.ProgramareConvert;
import com.example.demo.repository.*;
import com.example.demo.service.ServicePacient;
import com.example.demo.service.ServiceProgramari;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    static IRepository<Pacient> repoPacient = null;
    static IRepository<Programare> repoProgramare = null;
    static PacientConvert pacientConvert = new PacientConvert();
    static ProgramareConvert programareConvert = new ProgramareConvert();
    static Settings setari = Settings.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pacient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("CLINICA");
        stage.setScene(scene);
        stage.show();



        if (Objects.equals(setari.getRepoType(), "memory")) {
            repoPacient = new Repository<Pacient>();
            repoProgramare = new Repository<Programare>();
        }

        if (Objects.equals(setari.getRepoType(), "text")) {
            /*
            repo_type: text
            repo_file1: /Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/a4-aluas21/src/main/java/Pacienti.txt
            repo_file2: /Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/a4-aluas21/src/main/java/Programari.txt
             */
            repoPacient = new TextRepository<>(setari.getRepoFile1(), pacientConvert);
            repoProgramare = new TextRepository<>(setari.getRepoFile2(),programareConvert);
        }

        if (Objects.equals(setari.getRepoType(), "bin")) {
            repoPacient = new BinaryRepository<>(setari.getRepoFile1());
            repoProgramare = new BinaryRepository<>(setari.getRepoFile2());
        }

        if (Objects.equals(setari.getRepoType(), "db")) {
            repoPacient = new RepoPacientiDB();
            repoProgramare = new RepoProgramariDB(repoPacient);
        }


        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);
        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);


        ClinicaController pacientController = fxmlLoader.getController();

        pacientController.setService(servicePacient, serviceProgramari);

    }

    public static void main(String[] args) {
        launch();
        if (Objects.equals(setari.getRepoType(), "db")) {
            assert repoPacient != null;
            assert repoPacient instanceof RepoPacientiDB;
            ((RepoPacientiDB) repoPacient).closeConnection();
            ((RepoProgramariDB) repoProgramare).closeConnection();
        }
    }
}