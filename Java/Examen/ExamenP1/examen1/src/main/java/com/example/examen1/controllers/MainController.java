package com.example.examen1.controllers;

import com.example.examen1.domain.Entity;
import com.example.examen1.domain.Produs;
import com.example.examen1.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainController {
    public TextField ProdusMarcaField;
    public TextField ProdusNumeField;
    public TextField ProdusPretField;
    public TextField ProdusCantitateField;
    //tabel produs
    public TableView<Produs> tableViewProduse;
    public TableColumn<Produs,String> tableColumnId;
    public TableColumn<Produs,String> tableColumnMarca;
    public TableColumn<Produs,String> tableColumnNume;
    public TableColumn<Produs,String> tableColumnPret;
    public TableColumn<Produs,String> tableColumnCantitate;
    public TextField textFieldFiltrare;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");

    private Service serviceProdus;

    public void setService(Service serviceProdus){
        this.serviceProdus = serviceProdus;
        initmodel();
    }

    ObservableList<Produs> modelProdus = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            tableColumnId.setCellValueFactory(new PropertyValueFactory<Produs, String>("id"));
            tableColumnMarca.setCellValueFactory(new PropertyValueFactory<Produs, String>("marca"));
            tableColumnNume.setCellValueFactory(new PropertyValueFactory<Produs, String>("nume"));
            tableColumnPret.setCellValueFactory(new PropertyValueFactory<Produs, String>("pret"));
            tableColumnCantitate.setCellValueFactory(new PropertyValueFactory<Produs, String>("cantitate"));
            tableViewProduse.setItems(modelProdus);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //list view pacienti cu nr de programari
    @FXML
    ListView<String> listViewPacientiProgramari;

    //list view programari pe luna
    @FXML
    ListView<String> listViewProgramariLuna;

    //list view pacienti cu nr zile de la ultima programare
//    @FXML
//    ListView<String> listViewPacientiZileProg;


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



//    ObservableList<Pacient> modelPacienti = FXCollections.observableArrayList();
//    ObservableList<Programare> modelProgramari = FXCollections.observableArrayList();


    public void initmodel(){
        ArrayList<Produs> produse = serviceProdus.getAll();
        //sort by cantitate
        produse.sort((x,y)->y.getCantitate()-x.getCantitate());
        modelProdus.setAll(produse);

    }

    public void handleOptiune1(){
        try{
            //cauta cel mai mare id
            int maximId = 0;
            for(Produs produs : serviceProdus.getAll())
                if(produs.getId() > maximId)
                    maximId = produs.getId();
            int id = maximId+1;
            String nume = ProdusNumeField.getText();
            String marca = ProdusMarcaField.getText();
            int pret = Integer.parseInt(ProdusPretField.getText());
            int cantitate = Integer.parseInt(ProdusCantitateField.getText());
            if(cantitate<0||pret<=0||nume.equals("")||marca.equals(""))
                throw new RuntimeException("Date invalide");
            serviceProdus.addProdus(id,nume,marca,pret,cantitate);
            initialize();
            initmodel();
            //daca cantitatea e 0, seteaza pe linia respectiva pe coloana cantitate n/a
            if(cantitate == 0){
                tableViewProduse.getItems().get(id-1).setCantitate(-1);
            }

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Eroare la adaugare");
            alert.setContentText("Id-ul trebuie sa fie unic!");
            alert.showAndWait();
        }
        ProdusNumeField.clear();
        ProdusMarcaField.clear();
        ProdusPretField.clear();
        ProdusCantitateField.clear();

    }

    public void handleOptiune2(){

    }
    public void handleOptiune3(){

    }
    public void handleOptiune4(){

    }
    public void handleOptiune5() {

    }

    public void handleFiltrare(ActionEvent actionEvent) {
        try {
            String filtru = textFieldFiltrare.getText();
            serviceProdus.filtrare(filtru);
            initmodel();
            initialize();
            textFieldFiltrare.clear();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Eroare la filtrare");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        textFieldFiltrare.clear();

    }


//    @FXML
//    public void initialize() {
////        try {
////            tableColumnIdPacient.setCellValueFactory(new PropertyValueFactory<Pacient, String>("id"));
////            tableColumnName.setCellValueFactory(new PropertyValueFactory<Pacient, String>("name"));
////            tableColumnSurname.setCellValueFactory(new PropertyValueFactory<Pacient, String>("surname"));
////            tableColumnAge.setCellValueFactory(new PropertyValueFactory<Pacient, String>("age"));
////            tableViewPacienti.setItems(modelPacienti);
////
////
////            tableColumnIdProgramare.setCellValueFactory(new PropertyValueFactory<Programare, String>("id"));
////            tableColumnIdPacient2.setCellValueFactory(new PropertyValueFactory<Programare, String>("pacientId"));
////            tableColumnData.setCellValueFactory(new PropertyValueFactory<Programare, String>("date"));
////            tableColumnTime.setCellValueFactory(new PropertyValueFactory<Programare, String>("time"));
////            tableColumnMotiv.setCellValueFactory(new PropertyValueFactory<Programare, String>("motiv"));
////            tableViewProgramari.setItems(modelProgramari);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
