package com.example.demo;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.PacientConvert;
import com.example.demo.domain.Programare;
import com.example.demo.domain.ProgramareConvert;
import com.example.demo.repository.*;
import com.example.demo.service.ServicePacient;
import com.example.demo.service.ServiceProgramari;
import com.example.demo.ui.UI;

import java.util.Objects;

public class MainClass {
    public static void main(String[] args) {
        //       REPOSITORY CLASSIC
//        IRepository<Pacient> repoPacient = new Repository<>();
//        IRepository<Programare> repoProgramare = new Repository<>();


        //        REPOSITORY TEXT
//        PacientConvert convert = new PacientConvert();
//        ProgramareConvert convert1 = new ProgramareConvert();
//        IRepository<Pacient> repoPacient = new TextRepository<>("Pacienti.txt", convert);
//        IRepository<Programare> repoProgramare = new TextRepository<>("Programari.txt", convert1);


        //        REPOSITORY BINAR
//        IRepository<Pacient> repoPacient = repoPacient = new BinaryRepository<>("PacientiBinari.txt");
//        IRepository<Programare> repoProgramare = new BinaryRepository<>("ProgramariBinari.txt");



        /////           SETTINGS
        IRepository<Pacient> repoPacient = null;
        IRepository<Programare> repoProgramare = null;
        PacientConvert pacientConvert = new PacientConvert();
        ProgramareConvert programareConvert = new ProgramareConvert();

        Settings setari = Settings.getInstance();
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
        UI ui = new UI(servicePacient, serviceProgramari);
        ui.Meniu();

        if (Objects.equals(setari.getRepoType(), "db")) {
            ((RepoPacientiDB) repoPacient).closeConnection();
            ((RepoProgramariDB) repoProgramare).closeConnection();
        }

    }
}
