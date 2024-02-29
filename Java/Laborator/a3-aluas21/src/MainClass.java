import domain.Pacient;
import domain.PacientConvert;
import domain.Programare;
import domain.ProgramareConvert;
import repository.BinaryRepository;
import repository.IRepository;
import repository.Repository;
import repository.TextRepository;
import service.ServicePacient;
import service.ServiceProgramari;
import ui.UI;

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
            repoPacient = new TextRepository<>(setari.getRepoFile1(), pacientConvert);
            repoProgramare = new TextRepository<>(setari.getRepoFile2(),programareConvert);
        }

        if (Objects.equals(setari.getRepoType(), "bin")) {
            repoPacient = new BinaryRepository<>(setari.getRepoFile1());
            repoProgramare = new BinaryRepository<>(setari.getRepoFile2());
        }

        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);

        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);
        UI ui = new UI(servicePacient, serviceProgramari);
        ui.Meniu();
    }
}
