package com.example.practic.controllers;

import com.example.practic.domain.Entity;
import com.example.practic.domain.Quiz;
import com.example.practic.domain.QuizDTO;
import com.example.practic.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainController {

    public TextField textField;
    public TextField RaspunsAField;
    public TextField RaspunsBField;
    public TextField RaspunsCField;
    public TextField RaspunsCorectField;
    public TextField PunctajField;
    public ListView listViewQuiz;
    public ListView listViewQuizGen;
    public TextField PunctajMinField;
    public TextField PunctajMaxField;
    public TextField NrIntrebariField;
    Service service;


    public void setService(Service service){
        this.service = service;
        initmodel();
    }

    private void saveFile(ArrayList<Quiz> elems){
        try (FileWriter fw = new FileWriter("/Volumes/Macintosh HD/Desktop/PROGRAMARE/MAP/ExamenPractic/practic/src/main/java/com/example/practic/test.txt")) {
            for (Quiz object : elems) {
                fw.write(object.toString());
                fw.write("\r\n");
            }
        } catch (IOException e) {throw new RuntimeException(e);
        }
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
        //add to list dtoQuiz
        ArrayList<QuizDTO> list = service.getAllDTO();
        listViewQuiz.getItems().clear();
        for(QuizDTO p : list){
            listViewQuiz.getItems().add("id: " + p.getId()+ ", text: "+ p.getText()+" "+"punctaj: "+p.getPunctaj());
        }
    }

    public void Filtreaza(ActionEvent actionEvent) {
        try{
            int punctajMin = Integer.parseInt(PunctajMinField.getText());
            int punctajMax = Integer.parseInt(PunctajMaxField.getText());
            int nrIntrebari = Integer.parseInt(NrIntrebariField.getText());
            if(punctajMin<0 || punctajMax<0 || nrIntrebari<0)
                throw new Exception("Punctajul si numarul de intrebari trebuie sa fie pozitive");
            ArrayList<QuizDTO> list = service.generateQuiz(punctajMin,punctajMax,nrIntrebari);
            listViewQuizGen.getItems().clear();
            if(nrIntrebari>list.size())
                throw new Exception("Nu exista suficiente intrebari cu punctajul dorit");
            ArrayList<Quiz> quizzes = service.getAll();
            ArrayList<Quiz> quizzes2 = new ArrayList<>();
            for(int i = 0;i<quizzes.size();i++){
                for(int j = 0;j<list.size();j++){
                    if(quizzes.get(i).getId() == list.get(j).getId()){
                        quizzes2.add(quizzes.get(i));
                    }
                }
            }
            saveFile(quizzes2);
            for(QuizDTO p : list){
                listViewQuizGen.getItems().add("id: " + p.getId()+ ", text: "+ p.getText()+" "+"punctaj: "+p.getPunctaj());
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }

    public void handleAdd(ActionEvent actionEvent) {
        try{
            int maxiId = 0;
            ArrayList<Quiz> entities = service.getAll();
            for(Quiz e : entities){
                if(e.getId() > maxiId){
                    maxiId = e.getId();
                }
            }
            String text = textField.getText();
            String raspunsA = RaspunsAField.getText();
            String raspunsB = RaspunsBField.getText();
            String raspunsC = RaspunsCField.getText();
            String raspunsCorect = RaspunsCorectField.getText();
            int punctaj = Integer.parseInt(PunctajField.getText());
            if(punctaj<0)
                throw new Exception("Punctajul trebuie sa fie pozitiv");
            service.addEntity(maxiId+1,text,raspunsA,raspunsB,raspunsC,raspunsCorect,punctaj);
            initmodel();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        //clear
        textField.clear();
        RaspunsAField.clear();
        RaspunsBField.clear();
        RaspunsCField.clear();
        RaspunsCorectField.clear();
        PunctajField.clear();

    }
}



///THREADS
//int numbers = 0;
//        try {
//numbers = Integer.parseInt(txtNumbers.getText());
//        } catch (NumberFormatException nfe) {
//Alert hello = new Alert(Alert.AlertType.ERROR, "Enter a valid, positive integer");
//            hello.show();
//            return;
//                    }
//
//
////        try {
////            Thread.sleep(5000);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//
//
////        Thread -- modeleaza un fir de executie
////          Runnable -- are o metoda run si o pot folosi intr-un Thread
//
//int threads = Integer.parseInt(txtThreads.getText());
//int finalNumbers = numbers;
//int[] numberArray = Util.generate(finalNumbers);
//
//Thread[] threadArray = new Thread[threads];
//        for (int i = 0; i < threads; i++) {
//threadArray[i] = new Thread(new Runnable() {
//    @Override
//    public void run() {
//        int total = Util.countPrimes(numberArray, threadArray.length);
//        listData.add("Total primes = " + total);
//    }
//});
//        }
//long t1 = System.currentTimeMillis();
//
//        for (var t : threadArray) {
//        t.start();
//        }
//
//                for (var t : threadArray) {
//        t.join();
//        }
//long timeDelta = System.currentTimeMillis() - t1;
//        listData.add("time = " + timeDelta + " miliseconds.");
//
//
//
