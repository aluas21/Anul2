import domain.Pacient;
import domain.Programare;
import repository.Repository;
import service.ServicePacient;
import service.ServiceProgramari;
import ui.UI;
/*
TO DO
- exceptii pentru citire
- Existenta Pacient la: update si delete
- Id unic pentru fiecare pacient
- Validator pentru pacient
- TESTE

 */
public class MainClass {
    public static void main(String[] args) {

        Repository<Pacient> repoPacient = new Repository<>();
        Repository<Programare> repoProgramare = new Repository<>();

        ServiceProgramari serviceProgramari = new ServiceProgramari(repoProgramare, repoPacient);

        ServicePacient servicePacient = new ServicePacient(repoPacient, serviceProgramari);
        UI ui = new UI(servicePacient, serviceProgramari);
        ui.Meniu();
    }
}
