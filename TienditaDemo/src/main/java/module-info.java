module com.example.tienditademo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tienditademo to javafx.fxml;
    exports com.example.tienditademo;
    opens com.example.tienditademo.controllers to javafx.fxml;
    exports com.example.tienditademo.controllers;
    opens com.example.tienditademo.models to javafx.base;
}