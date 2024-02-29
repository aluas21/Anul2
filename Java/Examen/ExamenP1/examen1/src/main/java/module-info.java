module com.example.examen1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
//    requires org.xerial.sqlitejdbc;

    opens com.example.examen1 to javafx.fxml;
    exports com.example.examen1;
    exports com.example.examen1.domain;
    exports com.example.examen1.controllers;
    opens com.example.examen1.controllers to javafx.fxml;
}