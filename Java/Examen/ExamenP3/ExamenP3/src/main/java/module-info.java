module com.example.examenp3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires junit;

    opens com.example.examenp3 to javafx.fxml;
    exports com.example.examenp3;
    exports com.example.examenp3.controllers;
    opens com.example.examenp3.controllers to javafx.fxml;
}