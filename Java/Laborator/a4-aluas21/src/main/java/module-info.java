module com.example.demo {
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.naming;
    requires junit;
    requires org.junit.jupiter.api;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;

    opens com.example.demo.domain to javafx.base;
    opens com.example.demo.domain.dto to javafx.base;

}