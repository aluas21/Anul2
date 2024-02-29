package com.example.examenp3.controllers;

import com.example.examenp3.SettingsDescriere;
import com.example.examenp3.domain.Activitate;
import com.example.examenp3.domain.Entity;
import com.example.examenp3.exception.RepositoryException;
import com.example.examenp3.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ListViewSkin;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainController {

    public TextField dataField;
    public TextField nrPasiField;
    public TextField descriereField;
    public TextField durataField;
    public ListView listViewActivitati;
    public ComboBox comboboxDescriere;
    public TextField filtrare;
    Service service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    ObservableList<Entity> modelPacienti = FXCollections.observableArrayList();

    @FXML
    ListView<String> listViewEntity;

    public void setService(Service service){
        this.service = service;
        SettingsDescriere settingsDescriere = SettingsDescriere.getInstance();
        String descrieri = settingsDescriere.getDescriere();
        String[] descriere = descrieri.split(",");
        comboboxDescriere.getItems().addAll(descriere);
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
        //pune in arralist toate activitatile sortate

//        modelPacienti.setAll(servicePacient.getAll());
//        modelProgramari.setAll(serviceProgramari.getAll());
//
        //Adaugare pacienti cu nr programari in lista
        ArrayList<Activitate> listPa = service.sortbyNrPasi();
        listViewActivitati.getItems().clear();
        for(Activitate p : listPa){
            listViewActivitati.getItems().add(p.toString());
        }
    }

    public void handleAddActivitate(ActionEvent actionEvent) {
        try{
            int maximId = 0;
            for(Activitate produs : service.getAll())
                if(produs.getId() > maximId)
                    maximId = produs.getId();
            int id = maximId+1;
            LocalDate date = LocalDate.parse(dataField.getText());
            int nrPasi = Integer.parseInt(nrPasiField.getText());
            String descriere = comboboxDescriere.getValue().toString();
            int durata = Integer.parseInt(durataField.getText());
            service.addActivitate(id,date,nrPasi,descriere,durata);
            if(nrPasi<0||durata<0)
                throw new RuntimeException();
            initialize();
            initmodel();


        }catch (RuntimeException |RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }


    public void Filtreaza(ActionEvent actionEvent) {
        try {
            String filtru = filtrare.getText();
            service.filtrare(filtru);
            initmodel();
            initialize();
            filtrare.clear();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Eroare la filtrare");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        filtrare.clear();
    }
}
