import Domain.BMI;
import Domain.BP;
import repository.Repository;
import service.ServiceBMI;
import service.ServiceBP;
import UI.UI;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Repository<BMI> repo = new Repository<>();
        Repository<BP> repo2 = new Repository<>();
        ServiceBMI serviceBMI = new ServiceBMI(repo);
        ServiceBP serviceBP = new ServiceBP(repo2);
        UI ui = new UI(serviceBP, serviceBMI);
        ui.Meniu();
    }
}