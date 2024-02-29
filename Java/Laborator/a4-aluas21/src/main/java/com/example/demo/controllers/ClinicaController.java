package com.example.demo.controllers;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.domain.dto.PacientNrProg;
import com.example.demo.domain.dto.PacientZileProg;
import com.example.demo.domain.dto.ProgramariLuna;
import com.example.demo.exception.RepositoryException;
import com.example.demo.service.ServicePacient;
import com.example.demo.service.ServiceProgramari;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClinicaController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");
    @FXML
    Label welcomeText = new Label();
    private ServicePacient servicePacient;
    private ServiceProgramari serviceProgramari;
    //tabel pacienti
    @FXML
    TableView<Pacient> tableViewPacienti;
    @FXML
    TableColumn<Pacient,String> tableColumnIdPacient;
    @FXML
    TableColumn<Pacient,String> tableColumnName;
    @FXML
    TableColumn<Pacient,String> tableColumnSurname;
    @FXML
    TableColumn<Pacient,String> tableColumnAge;

    //tabel programari
    @FXML
    TableView<Programare> tableViewProgramari;
    @FXML
    TableColumn<Programare,String> tableColumnIdProgramare;
    @FXML
    TableColumn<Programare,String> tableColumnIdPacient2;
    @FXML
    TableColumn<Programare,String> tableColumnData;
    @FXML
    TableColumn<Programare,String> tableColumnTime;
    @FXML
    TableColumn<Programare,String> tableColumnMotiv;


    //list view pacienti cu nr de programari
    @FXML
    ListView<String> listViewPacientiProgramari;

    //list view programari pe luna
    @FXML
    ListView<String> listViewProgramariLuna;

    //list view pacienti cu nr zile de la ultima programare
    @FXML
    ListView<String> listViewPacientiZileProg;

    //ADAUGARE PACIENTI
    @FXML
    TextField pacientIdField;
    @FXML
    TextField pacientNumeField;
    @FXML
    TextField pacientPrenumeField;
    @FXML
    TextField pacientVarstaField;

    //ADAUGARE PROGRAMARE
    @FXML
    TextField programareIdField;
    @FXML
    TextField programareIdPacientField;
    @FXML
    TextField programareDataField;
    @FXML
    TextField programareTimeField;
    @FXML
    TextField programareMotivField;

    //STERGE PACIENT
    @FXML
    TextField stergePacientIdField;

    //STERGE PROGRAMARE
    @FXML
    TextField stergeProgramareIdField;

    //UPDATE PACIENT
    @FXML
    TextField updatePacientIdField;
    @FXML
    TextField updatePacientNumeField;
    @FXML
    TextField updatePacientPrenumeField;
    @FXML
    TextField updatePacientVarstaField;



    ObservableList<Pacient> modelPacienti = FXCollections.observableArrayList();
    ObservableList<Programare> modelProgramari = FXCollections.observableArrayList();
//    ObservableList<ProgramariLuna> modelProgramariLuna = FXCollections.observableArrayList();


    public void setService(ServicePacient servicePacient, ServiceProgramari serviceProgramari) {
        this.servicePacient = servicePacient;
        this.serviceProgramari = serviceProgramari;
        initModel();
    }

    @FXML
    public void initialize() {
        try {
            tableColumnIdPacient.setCellValueFactory(new PropertyValueFactory<Pacient, String>("id"));
            tableColumnName.setCellValueFactory(new PropertyValueFactory<Pacient, String>("name"));
            tableColumnSurname.setCellValueFactory(new PropertyValueFactory<Pacient, String>("surname"));
            tableColumnAge.setCellValueFactory(new PropertyValueFactory<Pacient, String>("age"));
            tableViewPacienti.setItems(modelPacienti);


            tableColumnIdProgramare.setCellValueFactory(new PropertyValueFactory<Programare, String>("id"));
            tableColumnIdPacient2.setCellValueFactory(new PropertyValueFactory<Programare, String>("pacientId"));
            tableColumnData.setCellValueFactory(new PropertyValueFactory<Programare, String>("date"));
            tableColumnTime.setCellValueFactory(new PropertyValueFactory<Programare, String>("time"));
            tableColumnMotiv.setCellValueFactory(new PropertyValueFactory<Programare, String>("motiv"));
            tableViewProgramari.setItems(modelProgramari);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    private void initModel() {
        modelPacienti.setAll(servicePacient.getAll());
        modelProgramari.setAll(serviceProgramari.getAll());

        //Adaugare pacienti cu nr programari in lista
        ArrayList<PacientNrProg> listPa = servicePacient.sortbynrptog();
        listViewPacientiProgramari.getItems().clear();
        for(PacientNrProg p : listPa){
            listViewPacientiProgramari.getItems().add("id: " + p.getId()+ ", name: "+ p.getName()+" "+"numar programari: "+p.getNrProg());
        }


        //Adaugare programari pe luna in lista
        ArrayList<ProgramariLuna> list = serviceProgramari.countProgramariLuna();
        listViewProgramariLuna.getItems().clear();
        for(ProgramariLuna p : list){
            listViewProgramariLuna.getItems().add("Luna: " + p.getLuna()+ ", numar programari: "+p.getNrProg());
        }

        //Adaugare pacienti cu nr zile de la ultima programare in lista
        ArrayList<PacientZileProg> listPacientiZileProg = servicePacient.sortbyzileptog();
        listViewPacientiZileProg.getItems().clear();
        for(PacientZileProg p : listPacientiZileProg){
            listViewPacientiZileProg.getItems().add("id: " + p.getId()+ ", name: "+ p.getName()+" "+"numar zile de la ultima programare: "+p.getDays());
        }
    }

    public void handleOption1() {
        System.out.println("Option 1 selected");
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        welcomeText.relocate(100,100);
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleAdaugaPacient(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(pacientIdField.getText());
            String name = pacientNumeField.getText();
            String surname = pacientPrenumeField.getText();
            int age = Integer.parseInt(pacientVarstaField.getText());
            servicePacient.addPacient(id,name,surname,age);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare pacient");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        pacientIdField.clear();
        pacientNumeField.clear();
        pacientPrenumeField.clear();
        pacientVarstaField.clear();
    }


    public void handleAdaugaProgramare(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(programareIdField.getText());
            int idPacient = Integer.parseInt(programareIdPacientField.getText());
            String data = programareDataField.getText();
            String time = programareTimeField.getText();
            String motiv = programareMotivField.getText();
            serviceProgramari.addProgramare(id,idPacient, LocalDate.parse(data,formatter), LocalTime.parse(time,formatter2),motiv);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare programare");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        programareIdField.clear();
        programareIdPacientField.clear();
        programareDataField.clear();
        programareTimeField.clear();
        programareMotivField.clear();
    }

    public void handleStergePacient(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(stergePacientIdField.getText());
            servicePacient.deletePacient(id);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Stergere pacient");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        stergePacientIdField.clear();
    }

    public void handleStergeProgramare(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(stergeProgramareIdField.getText());
            serviceProgramari.deleteProgramareById(id);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Stergere programare");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        stergeProgramareIdField.clear();
    }

    public void handleUpdatePacient(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(updatePacientIdField.getText());
            String name = updatePacientNumeField.getText();
            String surname = updatePacientPrenumeField.getText();
            int age = Integer.parseInt(updatePacientVarstaField.getText());
            servicePacient.updatePacient(id,name,surname,age);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Update pacient");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        updatePacientIdField.clear();
        updatePacientNumeField.clear();
        updatePacientPrenumeField.clear();
        updatePacientVarstaField.clear();
    }
}
