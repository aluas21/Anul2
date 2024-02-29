module com.example.practic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
//    requires junit;

    opens com.example.practic to javafx.fxml;
    exports com.example.practic;
    exports com.example.practic.controllers;
    opens com.example.practic.controllers to javafx.fxml;
    exports com.example.practic.tests;
    opens com.example.practic.tests to javafx.fxml;

}