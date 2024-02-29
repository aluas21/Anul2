package com.example.demo.ui;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.exception.RepositoryException;
import com.example.demo.service.ServicePacient;
import com.example.demo.service.ServiceProgramari;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    ServicePacient servicePacient;
    ServiceProgramari serviceProgramari;
    Scanner read = new Scanner(System.in);

    public UI(ServicePacient servicePacient, ServiceProgramari serviceProgramare){
        this.servicePacient = servicePacient;
        this.serviceProgramari = serviceProgramare;
    }

    void showOption(){
        System.out.println("        MENIU       ");
        System.out.println(" ");
        System.out.println("1. Add Pacient");
        System.out.println("2. Delete Pacient");
        System.out.println("3. Update Pacient");
        System.out.println("4. Add Programare");
        System.out.println("5. Delete Programare");
        System.out.println("10. View all Pacienti");
        System.out.println("11. View all Programari");
        System.out.println("0. STOP");
    }

    void addPacient() throws RepositoryException {
        System.out.print("  ID: ");
        int id = Integer.parseInt(read.next());
        System.out.print("  Name: ");
        String name = read.next();
        System.out.print("  Surname: ");
        String surname = read.next();
        System.out.print("  Age: ");
        int age = Integer.parseInt(read.next());
        servicePacient.addPacient(id,name,surname,age);
    }


    void deletePacient() throws RepositoryException {
        System.out.print("  ID: ");
        int id = Integer.parseInt(read.next());
        servicePacient.deletePacient(id);
    }

    void updatePacient() throws RepositoryException {
        System.out.print("  ID: ");
        int id = Integer.parseInt(read.next());
        System.out.print("  New Name: ");
        String name = read.next();
        System.out.print("  New Surname: ");
        String surname = read.next();
        System.out.print("  New Age: ");
        int age = Integer.parseInt(read.next());
        servicePacient.updatePacient(id,name,surname,age);
    }

    void addProgramare() throws RepositoryException {
        System.out.print("  ID programare: ");
        int idProgramare = Integer.parseInt(read.next());
        System.out.print("  ID pacient: ");
        int idPacient = Integer.parseInt(read.next());
        System.out.print("  Date(yyyy/MM/dd): ");
        LocalDate date = LocalDate.parse(read.next(),formatter);
        System.out.print("  Time(h:m): ");
        LocalTime time = LocalTime.parse(read.next(),formatter2);
        System.out.print("  Motiv: ");
        String motiv = read.next();
        serviceProgramari.addProgramare(idProgramare,idPacient,date,time,motiv);

    }

    void deleteProgramare() throws RepositoryException {
        System.out.print("  ID: ");
        int id = Integer.parseInt(read.next());
        serviceProgramari.deleteProgramareById(id);
    }

    void viewPacienti(){
        ArrayList<Pacient> listPacienti = servicePacient.getAll();
        for(Pacient pacient: listPacienti)
            System.out.println(pacient.toString());
    }

    void viewProgramari(){
        ArrayList<Programare> listProgramari = serviceProgramari.getAll();
        for(Programare programare: listProgramari)
            System.out.println(programare.toString());
    }

    void addEntities() throws RepositoryException {
//        final String[] Name = new String[]{
//        "Aluas","Bucila","Pop","Badescu","Melean","Simionca","Pasc","Beldean"};
//        final String[] Surname = new String[]{
//                "Alin","Rares","Mihai","Alexandra","Gabriel","Stefania","Ionut","Ionela"};
//
//        for(int i = 2;i<=100;i++){
//            int index = (int)(Math.random() * 7);
//            int index2 = (int)(Math.random() * 7);
//            int age = (int)(Math.random() * 22) + 18;
//            servicePacient.addPacient(i,Name[index],Surname[index2],age);
//        }

//        ArrayList<String> motiv = new ArrayList<>(){};
//        motiv.add("bolnav");
//        motiv.add("racit");
//        motiv.add("alergie");
//        motiv.add("tratament");
//        motiv.add("consultatii");
//        motiv.add("tratament");
//        motiv.add("racit");
//        motiv.add("alergie");
//        ArrayList<LocalDate> listDate = new ArrayList<>();
//        for(int i = 0;i<100;i++){
//            int year = (int)(Math.random() * 3) + 2021;
//            int month = (int)(Math.random() * 12) + 1;
//            int day = (int)(Math.random() * 28) + 1;
//            LocalDate date = LocalDate.of(year,month,day);
//            listDate.add(date);
//        }
//        ArrayList<LocalTime> listTime = new ArrayList<>();
//        for(int i = 0;i<100;i++){
//            int hour = (int)(Math.random() * 23);
//            int minute = (int)(Math.random() * 59);
//            LocalTime time = LocalTime.of(hour,minute);
//            listTime.add(time);
//        }
//        //add programari
//        for (int i = 21;i<=100;i++){
//            int index = (int)(Math.random() * 40);
//            int index3 = (int)(Math.random() * 6);
//            int index2 = (int)(Math.random() * 99);
//            serviceProgramari.addProgramare(i,index,listDate.get(index2),listTime.get(index2),motiv.get(index3));
//        }




//        servicePacient.addPacient(1,"Aluas","Alin",19);
//        servicePacient.addPacient(2,"Bucila","Mihai",20);
//        servicePacient.addPacient(3,"Beledean","Stefania",29);
//        servicePacient.addPacient(4,"Badescu","Alexandra",39);
//        servicePacient.addPacient(5,"Timari","Ionut",18);
//        LocalTime time = LocalTime.parse("21:30",formatter2);
//        LocalDate date = LocalDate.parse("2022/11/22",formatter);
//        serviceProgramari.addProgramare(1,1,date,time, "racit");
//        time = LocalTime.parse("21:00",formatter2);
//        date = LocalDate.parse("2023/12/21",formatter);
//        serviceProgramari.addProgramare(2,1,date,time, "bolnav");
//        time = LocalTime.parse("10:20",formatter2);
//        date = LocalDate.parse("2023/02/21",formatter);
//        serviceProgramari.addProgramare(3,2,date,time, "racit");
//        time = LocalTime.parse("11:30",formatter2);
//        date = LocalDate.parse("2024/04/20",formatter);
//        serviceProgramari.addProgramare(4,3,date,time, "bolnav");
//        time = LocalTime.parse("14:45",formatter2);
//        date = LocalDate.parse("2022/04/20",formatter);
//        serviceProgramari.addProgramare(5,2,date,time, "racit");
//        time = LocalTime.parse("17:10",formatter2);
//        date = LocalDate.parse("2021/04/23",formatter);
//        serviceProgramari.addProgramare(6,3,date,time, "racit");
//        time = LocalTime.parse("20:32",formatter2);
//        date = LocalDate.parse("2022/10/14",formatter);
//        serviceProgramari.addProgramare(7,4,date,time, "racit");

    }
    public void Meniu(){
//        try {
//            this.addEntities();
//        }
//        catch (RepositoryException e){
//            System.out.println(e);
//        }
        String opt = "1";
        while(!opt.equals("0")){
            try{
                this.showOption();
                opt = read.next();
                switch (opt) {
                    case "1": {
                        this.addPacient();
                        break;
                    }
                    case "2": {
                        this.deletePacient();
                        break;
                    }
                    case "3": {
                        this.updatePacient();
                        break;
                    }
                    case "4": {
                        this.addProgramare();
                        break;
                    }
                    case "5": {
                        this.deleteProgramare();
                        break;
                    }
                    case "10": {
                        this.viewPacienti();
                        break;
                    }
                    case "11": {
                        this.viewProgramari();
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
            catch (RepositoryException e){
                System.out.println(e.toString());
            }
        }
    }
}