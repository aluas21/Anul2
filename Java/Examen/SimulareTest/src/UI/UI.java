package UI;

import Domain.BMI;
import Domain.BP;
import service.ServiceBMI;
import service.ServiceBP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class UI {
    Scanner read = new Scanner(System.in);

    ServiceBP serviceBP;
    ServiceBMI  serviceBMI;
    public UI(ServiceBP serviceBP, ServiceBMI serviceBMI){
        this.serviceBP = serviceBP;
        this.serviceBMI = serviceBMI;
    }

    public void PrintOptions(){
        System.out.println("     MENIU    ");
        System.out.println("1. Add BMI");
        System.out.println("2. Add BP");
        System.out.println("3. View all");
        System.out.println("4. Verify Health");
        System.out.println("5. SaveToFile");
        System.out.println("0. STOP");

    }

    private void addData(){
        serviceBP.addBP("1",200, 80);
        serviceBP.addBP("2",120, 70);
        serviceBP.addBP("3",120, 70);
        serviceBMI.addBMI("1",27);
        serviceBMI.addBMI("2",20);
        serviceBMI.addBMI("3",21);

    }

    public void Meniu(){
        addData();
        String opt = "1";
        while(!opt.equals("0")){
            try{
                this.PrintOptions();
                opt = read.next();
                switch (opt) {
                    case "1": {
                        this.addBMI();
                        break;
                    }
                    case "2": {
                        this.addBP();
                        break;
                    }
                    case "3": {
                        this.viewAll();
                        break;
                    }
                    case "4": {
                        this.verifyHealth();
                        break;
                    }
                    case "5": {
                        this.saveToFile();
                        break;
                    }

                    case "0": {
                        break;
                    }
                    default:{
                        System.out.println("This option was not found!");
                    }
                }
            }
            catch (RuntimeException e){
                System.out.println(e);
            }
        }

    }

    private void saveToFile() {
        System.out.println("Insert data: ");
        String date = read.next();
        ArrayList<BMI> listBMI = serviceBMI.getAfterData(date);
        ArrayList<BP> listBP = serviceBP.getAfterData(date);
        try (FileWriter fw = new FileWriter("data.txt")) {
            for (BMI T : listBMI) {
                fw.write(T.toString());
                fw.write("\r\n");
            }
            for (BP T : listBP) {
                fw.write(T.toString());
                fw.write("\r\n");
            }
            System.out.println("Data was saved to file!");

        } catch (IOException e) {throw new RuntimeException(e);
        }


    }

    private void verifyHealth() {
        System.out.println("Insert date: ");
        String date = read.next();
        if(serviceBMI.isNormalValue(date) && serviceBP.isNormalValue(date))
            System.out.println("You are healthy!");
        else
            System.out.println("You are not healthy!");

    }

    private void viewAll() {
        System.out.println("BMI:");
        for(BMI bmi: serviceBMI.getAll()){
            System.out.println(bmi);
        }
        System.out.println(" ");
        System.out.println("BP: ");
        for(BP bp: serviceBP.getAll()){
            System.out.println(bp);
        }
    }

    private void addBP() {
        System.out.println("Insert date: ");
        String date = read.next();
        System.out.print("Insert systolic value: ");
        int systolicValue = read.nextInt();
        System.out.print("Insert diastolic value: ");
        int diastolicValue = read.nextInt();
        serviceBP.addBP(date,systolicValue, diastolicValue);
    }

    private void addBMI() {
        System.out.println("Insert date: ");
        String date = read.next();
        System.out.print("Insert value: ");
        float value = read.nextFloat();
        serviceBMI.addBMI(date,value);
    }

}
