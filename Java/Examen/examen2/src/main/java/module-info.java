module com.example.examen2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.examen2 to javafx.fxml;
    exports com.example.examen2;
    exports com.example.examen2.controllers;
    opens com.example.examen2.controllers to javafx.fxml;
}