package com.example.examen2.controllers;

import com.example.examen2.SettingDescriere;
import com.example.examen2.domain.Activitate;
import com.example.examen2.domain.Entity;
import com.example.examen2.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainController {

    public TextField DataField;
    public TextField OraField;
    public TextField NrPasiField;
    public ComboBox comboboxDescriere;
    public TextField Minute;
    public TextField minuteMin;
    public TextField minuteMax;
    public ListView listActivitatiFiltare;
    Service service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    ObservableList<Entity> modelPacienti = FXCollections.observableArrayList();

    @FXML
    ListView<String> listActivitati;

    public void setService(Service service){
        this.service = service;
        initmodel();
    }



    @FXML
    public void initialize() {
        try {

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initmodel(){
        ArrayList<Activitate> activitati = service.getActivitati();
        listActivitati.getItems().clear();
        for(Activitate a : activitati){
            listActivitati.getItems().add(a.ToString());
        }

        SettingDescriere settingDescriere = SettingDescriere.getInstance();
        String descrieri = settingDescriere.getDescriere();
        String[] descriere = descrieri.split(",");
        comboboxDescriere.getItems().addAll(descriere);

//        modelPacienti.setAll(servicePacient.getAll());
//        modelProgramari.setAll(serviceProgramari.getAll());
//
//        //Adaugare pacienti cu nr programari in lista
//        ArrayList<PacientNrProg> listPa = servicePacient.sortbynrptog();
//        listViewPacientiProgramari.getItems().clear();
//        for(PacientNrProg p : listPa){
//            listViewPacientiProgramari.getItems().add("id: " + p.getId()+ ", name: "+ p.getName()+" "+"numar programari: "+p.getNrProg());
//        }
    }

    public void adauga(ActionEvent actionEvent) {
        try{
            int maxId = 0;
            ArrayList<Activitate> activitati = service.sortByDate();
            for(Activitate a : activitati){
                if(a.getId() > maxId){
                    maxId = a.getId();
                }
            }
            maxId++;
            LocalDate data = LocalDate.parse( DataField.getText());
            String ora = OraField.getText();
            String nrPasi = NrPasiField.getText();
            String descriere = comboboxDescriere.getValue().toString();
            String minute = Minute.getText();
            service.addActivitate(maxId,data, nrPasi, descriere, minute);
            initmodel();
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        //clear
        DataField.clear();
        OraField.clear();
        NrPasiField.clear();
        Minute.clear();

    }

    public void filtreazaIntre(ActionEvent actionEvent) {
        try{
            String minuteMinim = minuteMin.getText();
            String minuteMaxim = minuteMax.getText();
            ArrayList<Activitate> activitati = service.getActivitati();
            listActivitatiFiltare.getItems().clear();
            for(Activitate a : activitati){
                int minute = 0;
                for(int i = 0; i < a.getActiuni().size(); i++){
                    minute += a.getActiuni().get(i).getDurata();
                }
                if(minute >= Integer.parseInt(minuteMinim) && minute <= Integer.parseInt(minuteMaxim)){
                    listActivitatiFiltare.getItems().add(a.ToString());
                }
            }
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
}
