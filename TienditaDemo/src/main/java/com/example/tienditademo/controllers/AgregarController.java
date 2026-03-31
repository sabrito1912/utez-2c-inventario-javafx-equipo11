package com.example.tienditademo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AgregarController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtCatalogo;

    public void agregar(ActionEvent actionEvent) {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String precio = txtPrecio.getText();
        String stock = txtStock.getText();
        String catalogo = txtCatalogo.getText();
    }

    public void regresar(ActionEvent actionEvent) {
    }
}
